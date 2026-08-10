interface full_adder_if;
    logic a, b, cin;
    logic sum, cout;
endinterface

class transaction;
    rand bit a, b, cin;
    bit sum, cout;

    function void display(string tag = "");
        $display("%-15 a=%b b=%b cin=%b | sum=%b cout=%b", tag, a, b, cin, sum, cout);
    endfunction
endclass

class generator;

    mailbox #(transaction) gen2drv;
    int mode;

    function new (mailbox #(transaction) gen2drv, int mode = 0);
        this.gen2drv = gen2drv;
        this.mode = mode;
    endfunction

    task run();
        transaction t;

        if (mode == 0) begin
            repeat (10) begin
                t = new();
                assert(t.randomize());
                t.display("[GEN-RANDOM]");
                gen2drv.put(t);
                #10;
            end
        end
        else begin

            for (int i = 0; i < 8; i++) begin
                t = new();
                t.a = i[2];
                t.b = i[1];
                t.cin = i[0];
                t.display("[GEN-DIRECTED]");
                gen2drv.put(t);
                #10;
            end
        end
    endtask
endclass

class driver;

    virtual full_adder_if tb_if;
    mailbox #(transaction) gen2drv;
    mailbox #(transaction) drv2scb;

    function new(virtual full_adder_if tb_if, mailbox #(transaction) gen2drv, mailbox #(transaction) drv2scb);
        this.tb_if = tb_if;
        this.gen2drv = gen2drv;
        this.drv2scb = drv2scb;
    endfunction

    task run();
        transaction t;
        forever begin
            gen2drv.get(t);
            
            tb_if.a = t.a;
            tb_if.b = t.b;
            tb_if.cin = t.cin;

            t.display("[DRV]");
            drv2scb.put(t);
            #10;
        end
    endtask
endclass

class monitor;

    virtual full_adder_if tb_if;
    mailbox #(transaction) mon2scb;

    covergroup input_coverage;
        option.per_instance = 1;

        cp_a : coverpoint tb_if.a;
        cp_b : coverpoint tb_if.b;
        cp_cin : coverpoint tb_if.cin;

        cross_abc : cross cp_a, cp_b, cp_cin;
    endgroup

    function new(virtual full_adder_if tb_if, mailbox #(transaction) mon2scb);
        this.tb_if = tb_if;
        this.mon2scb = mon2scb;
        input_coverage = new();
    endfunction

    task run();
        transaction t;
        forever begin
            t = new();
            #9;
            t.a = tb_if.a;
            t.b = tb_if.b;
            t.cin = tb_if.cin;
            t.sum = tb_if.sum;
            t.cout = tb_if.cout;

            input_coverage.sample();
            mon2scb.put(t);
            t.display("[MON]");
            #1;
        end
    endtask
endclass

class scoreboard;

    mailbox #(transaction) drv2scb;
    mailbox #(transaction) mon2scb;
    transaction from_drv, from_mon;
    bit expected_sum, expected_cout;

    function new(mailbox #(transaction) drv2scb, mailbox #(transaction) mon2scb);
        this.drv2scb = drv2scb;
        this.mon2scb = mon2scb;
    endfunction

    task run();
        forever begin
            drv2scb.get(from_drv);
            mon2scb.get(from_mon);

            expected_sum = from_drv.a ^ from_drv.b ^ from_drv.cin;
            expected_cout = (from_drv.a & from_drv.b) | (from_drv.b & from_drv.cin) | (from_drv.cin & from_drv.a);

            if(expected_sum !== from_mon.sum || expected_cout !== from_mon.cout)
                $display("[SCB] FAIL ");
            else
                $display("[SCB] PASS ");
        end
    endtask
endclass   

class agent;

    driver drv;
    monitor mon;
    bit is_active;

    function new(virtual full_adder_if tb_if, mailbox #(transaction) gen2drv, mailbox #(transaction) drv2scb, mailbox #(transaction) mon2scb, bit is_active);
        this.is_active = is_active;

        if(is_active)
            drv = new(tb_if, gen2drv, drv2scb);
        
        mon = new(tb_if, mon2scb);
    endfunction

    task run();
        if(is_active) begin
            fork
                drv.run();
                mon.run();
            join_none
        end
        else begin
            mon.run();
        end
    endtask
endclass

class environment;

    generator gen;
    agent active_agent;
    agent passive_agent;
    scoreboard scb;

    mailbox #(transaction) gen2drv;
    mailbox #(transaction) drv2scb;
    mailbox #(transaction) mon2scb;

    function new(virtual full_adder_if tb_if, int mode);
        gen2drv = new();
        drv2scb = new();
        mon2scb = new();

        gen = new(gen2drv, mode);
        active_agent = new(tb_if, gen2drv, drv2scb, mon2scb, 1);
        passive_agent = new(tb_if, null, null, mon2scb, 0);
        scb = new(drv2scb, mon2scb);
    endfunction

    task run();
        fork
            gen.run();
            active_agent.run();
            passive_agent.run();
            scb.run();
        join_none
    endtask
endclass

class base_test;

    environment env;
    virtual full_adder_if tb_if;
    
    function new(virtual full_adder_if tb_if, int mode);
        this.tb_if = tb_if;
        env = new(tb_if, mode);
    endfunction

    task run();
        env.run();
        #200;
        $finish;
    endtask
endclass

class random_test extends base_test;
    function new(virtual full_adder_if tb_if);
        super.new(tb_if, 0);
    endfunction
endclass

class directed_test extends base_test;
    function new(virtual full_adder_if tb_if);
        super.new(tb_if, 1);
    endfunction
endclass

module testbench;

    full_adder_if tb_if();

    full_adder dut (
        .a (tb_if.a),
        .b (tb_if.b),
        .cin (tb_if.cin),
        .sum (tb_if.sum),
        .cout (tb_if.cout)
    );

    directed_test t;

    initial begin
        $dumpfile("dump.vcd");
        $dumpvars;

        t = new(tb_if);
        t.run();
    end
endmodule