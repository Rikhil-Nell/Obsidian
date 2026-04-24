# Just Testbench and Design (week - 1)

```verilog
// Code your testbench here
// or browse Examples
interface full_adder_if;
  logic a,b,cin;
  logic sum,cout;
endinterface

module full_adder_testbench;
  
  full_adder_if tb_if();
  
  full_adder dut(
  .a(tb_if.a),
  .b(tb_if.b),
  .cin(tb_if.cin),
  .sum(tb_if.sum),
  .cout(tb_if.cout)
  );
  
  initial begin
    $dumpfile("dump.vcd");
    $dumpvars;
    
    tb_if.a=0;tb_if.b=0;tb_if.cin=0;
    #10;
    tb_if.a=1;tb_if.b=0;tb_if.cin=1;
    #10;
    tb_if.a=0;tb_if.b=1;tb_if.cin=0;
    #10;
    tb_if.a=1;tb_if.b=1;tb_if.cin=1;
    #10;
    
    $finish;
  end
endmodule
```

```verilog
// DUT (Full Adder)
module full_adder (
    input logic a, b, cin,
    output logic sum, cout
);
    assign sum = a ^ b ^ cin; // Sum logic
    assign cout = (a & b) | (b & cin) | (cin & a); // Carry logic
endmodule
```

# With Driver (week - 2)

```verilog
// Code your testbench here
// or browse Examples

interface full_adder_if;
  logic a,b,cin;
  logic sum,cout;
endinterface

class driver;
  
  virtual full_adder_if tb_if;
  
  function new(virtual full_adder_if tb_if_instance);
    this.tb_if=tb_if_instance;
  endfunction
  
  task drive();
    tb_if.a=0;tb_if.b=0;tb_if.cin=0;
    #10;
    tb_if.a=1;tb_if.b=0;tb_if.cin=1;
    #10;
    tb_if.a=0;tb_if.b=1;tb_if.cin=0;
    #10;
    tb_if.a=1;tb_if.b=1;tb_if.cin=1;
    #10;
  endtask
endclass

module full_adder_testbench;
  
  full_adder_if tb_if();
  
  full_adder dut(
  .a(tb_if.a),
  .b(tb_if.b),
  .cin(tb_if.cin),
  .sum(tb_if.sum),
  .cout(tb_if.cout)
  );
  
  driver drv;
  
  initial begin
    $dumpfile("dump.vcd");
    $dumpvars;
    drv=new(tb_if);
    drv.drive();
    $finish;
  end
endmodule
```

```verilog
// DUT (Full Adder)
module full_adder (
    input logic a, b, cin,
    output logic sum, cout
);
    assign sum = a ^ b ^ cin; // Sum logic
    assign cout = (a & b) | (b & cin) | (cin & a); // Carry logic
endmodule
```

# With Driver and Monitor (week - 3)

```verilog
// Code your testbench here
// or browse Examples

interface full_adder_if;
  logic a,b,cin;
  logic sum,cout;
endinterface

class driver;
  
  virtual full_adder_if tb_if;
  //constructor
  function new(virtual full_adder_if tb_if_instance);
    this.tb_if=tb_if_instance;
  endfunction
  //driver stimuli
  task drive();
    tb_if.a=0;tb_if.b=0;tb_if.cin=0;
    #10;
    tb_if.a=1;tb_if.b=0;tb_if.cin=1;
    #10;
    tb_if.a=0;tb_if.b=1;tb_if.cin=0;
    #10;
    tb_if.a=1;tb_if.b=1;tb_if.cin=1;
    #10;
  endtask
endclass

//Monitor
class monitor;
  virtual full_adder_if tb_if;
  
  function new(virtual full_adder_if tb_if_instance);
    this.tb_if = tb_if_instance;
  endfunction
  
  task observe();
    $display("Time\tA B Cin : Sum Cout");
    $display("=========================");
    
    repeat(4) begin
      #9;
      $display("%0t \t%b %b %b : %b %b", $time, tb_if.a, tb_if.b, tb_if.cin, tb_if.sum, tb_if.cout);
      
    end
  endtask
  endclass


module full_adder_testbench;
  
  full_adder_if tb_if();
  
  full_adder dut(
  .a(tb_if.a),
  .b(tb_if.b),
  .cin(tb_if.cin),
  .sum(tb_if.sum),
  .cout(tb_if.cout)
  );
  
  driver drv;
  monitor mon;
  
  initial begin
    $dumpfile("dump.vcd");
    $dumpvars;
    drv=new(tb_if);
    mon=new(tb_if);
    fork
    	drv.drive();
    	mon.observe();
    join
      $finish;
  end
endmodule
```

```verilog
// DUT (Full Adder)
module full_adder (
    input logic a, b, cin,
    output logic sum, cout
);
    assign sum = a ^ b ^ cin; // Sum logic
    assign cout = (a & b) | (b & cin) | (cin & a); // Carry logic
endmodule
```

# With Driver, Monitor and Scoreboard (week - 4)

```verilog
// Code your testbench here
// or browse Examples

interface full_adder_if;
  logic a, b, cin;
  logic sum, cout;
endinterface

class driver;
  virtual full_adder_if tb_if;
  
  // Constructor
  function new(virtual full_adder_if tb_if_instance);
    this.tb_if = tb_if_instance;
  endfunction
  
  // Driver stimuli
  task drive();
    tb_if.a = 0; tb_if.b = 0; tb_if.cin = 0;
    #10;
    tb_if.a = 1; tb_if.b = 0; tb_if.cin = 1;
    #10;
    tb_if.a = 0; tb_if.b = 1; tb_if.cin = 0;
    #10;
    tb_if.a = 1; tb_if.b = 1; tb_if.cin = 1;
    #10;
  endtask
endclass

// Monitor
class monitor;
  virtual full_adder_if tb_if;
  mailbox mon2scb; // Mailbox to send data to scoreboard

  function new(virtual full_adder_if tb_if_instance, mailbox mon2scb);
    this.tb_if = tb_if_instance;
    this.mon2scb = mon2scb;
  endfunction

  task observe();
    repeat(4) begin
      @(tb_if.a or tb_if.b or tb_if.cin); // Wait for signal change
      #1;  // Small settling delay
      mon2scb.put({tb_if.a, tb_if.b, tb_if.cin, tb_if.sum, tb_if.cout});
    end
  endtask
endclass

// Scoreboard
class scoreboard;
  mailbox mon2scb;

  // Constructor
  function new(mailbox mon2scb);
    this.mon2scb = mon2scb;
  endfunction

  task check();
    bit [4:0] received_data;
    bit expected_sum, expected_cout;
    
    repeat(4) begin
      mon2scb.get(received_data);
      
      // Calculate expected results based on input bits from received_data
      // [4]=a, [3]=b, [2]=cin, [1]=sum, [0]=cout (Based on monitor packing)
      // Note: Logic below follows the image's manual gate-level calculation
      expected_sum = received_data[4] ^ received_data[3] ^ received_data[2];
      expected_cout = (received_data[4] & received_data[3]) | 
                      (received_data[3] & received_data[2]) | 
                      (received_data[4] & received_data[2]);
      
      if (received_data[1] !== expected_sum || received_data[0] !== expected_cout) begin
        $display("ERROR at time %0t: Expected Sum=%b, Got Sum=%b | Expected Cout=%b, Got Cout=%b", 
                 $time, expected_sum, received_data[1], expected_cout, received_data[0]);
      end else begin
        $display("PASSED at time %0t: Sum=%b, Cout=%b", $time, received_data[1], received_data[0]);
      end
    end
  endtask
endclass

module full_adder_testbench;

  full_adder_if tb_if();

  // DUT Instance
  full_adder dut(
    .a(tb_if.a),
    .b(tb_if.b),
    .cin(tb_if.cin),
    .sum(tb_if.sum),
    .cout(tb_if.cout)
  );

  driver drv;
  monitor mon;
  scoreboard scb;
  mailbox mon2scb;

  initial begin
    $dumpfile("dump.vcd");
    $dumpvars;
    
    // Initialize components
    mon2scb = new();
    drv = new(tb_if);
    mon = new(tb_if, mon2scb);
    scb = new(mon2scb);
    
    // Run components in parallel
    fork
      drv.drive();
      mon.observe();
      scb.check();
    join
    
    $finish;
  end
endmodule
```

```verilog
// DUT (Full Adder)
module full_adder (
    input logic a, b, cin,
    output logic sum, cout
);
    assign sum = a ^ b ^ cin; // Sum logic
    assign cout = (a & b) | (b & cin) | (cin & a); // Carry logic
endmodule

```


# With Driver, Monitor, Generator, Transaction and Scoreboard (week - 5)

## Randomized Values

```verilog
// Interface
interface full_adder_if;
  logic a, b, cin;
  logic sum, cout;
endinterface


// Transaction
class transaction;
  rand bit a, b, cin;
  bit      sum, cout;

  function new();
  endfunction

  function void display(string msg = "");
    $display("%s A=%b B=%b Cin=%b | Sum=%b Cout=%b",
             msg, a, b, cin, sum, cout);
  endfunction
endclass


// Generator
class generator;
  mailbox #(transaction) gen2drv;

  function new(mailbox #(transaction) gen2drv);
    this.gen2drv          = gen2drv;
  endfunction

  task run();
    transaction trans;
    repeat (4) begin
      trans = new();
      assert (trans.randomize())
        else $display("Randomization failed at time %0t", $time);
      trans.display("GEN:");
      gen2drv.put(trans);
    end
  endtask
endclass


// Driver
class driver;
  virtual full_adder_if  tb_if;
  mailbox #(transaction) gen2drv;

  function new(virtual full_adder_if tb_if_instance,
               mailbox #(transaction) gen2drv);
    this.tb_if   = tb_if_instance;
    this.gen2drv = gen2drv;
  endfunction

  task run();
    transaction trans;
    repeat (4) begin
      gen2drv.get(trans);
      tb_if.a   = trans.a;
      tb_if.b   = trans.b;
      tb_if.cin = trans.cin;
      #10;
    end
  endtask
endclass


// Monitor
class monitor;
  virtual full_adder_if  tb_if;
  mailbox #(transaction) mon2scb;

  function new(virtual full_adder_if tb_if_instance,
               mailbox #(transaction) mon2scb);
    this.tb_if   = tb_if_instance;
    this.mon2scb = mon2scb;
  endfunction

  task run();
    transaction trans;
    repeat (4) begin
      @(tb_if.a or tb_if.b or tb_if.cin);
      #1; // small settling delay
      trans      = new();
      trans.a    = tb_if.a;
      trans.b    = tb_if.b;
      trans.cin  = tb_if.cin;
      trans.sum  = tb_if.sum;
      trans.cout = tb_if.cout;
      trans.display("MON:");
      mon2scb.put(trans);
    end
  endtask
endclass


// Scoreboard
class scoreboard;
  mailbox #(transaction) mon2scb;

  function new(mailbox #(transaction) mon2scb);
    this.mon2scb = mon2scb;
  endfunction

  task run();
    transaction trans;
    bit         expected_sum, expected_cout;

    repeat (4) begin
      mon2scb.get(trans);

      expected_sum  =  trans.a ^ trans.b ^ trans.cin;
      expected_cout = (trans.a & trans.b)
                    | (trans.b & trans.cin)
                    | (trans.a & trans.cin);

      if (trans.sum !== expected_sum || trans.cout !== expected_cout)
        $display("ERROR  at time %0t: Expected Sum=%b Got Sum=%b | Expected Cout=%b Got Cout=%b",
                 $time, expected_sum, trans.sum, expected_cout, trans.cout);
      else
        $display("PASSED at time %0t: Sum=%b Cout=%b", $time, trans.sum, trans.cout);
    end
  endtask
endclass


// Testbench Top
module full_adder_testbench;

  full_adder_if tb_if();

  full_adder dut (
    .a   (tb_if.a),
    .b   (tb_if.b),
    .cin (tb_if.cin),
    .sum (tb_if.sum),
    .cout(tb_if.cout)
  );

  generator              gen;
  driver                 drv;
  monitor                mon;
  scoreboard             scb;
  mailbox #(transaction) gen2drv;
  mailbox #(transaction) mon2scb;

  initial begin
    $dumpfile("dump.vcd");
    $dumpvars;

    gen2drv = new();
    mon2scb = new();
    gen     = new(gen2drv);
    drv     = new(tb_if, gen2drv);
    mon     = new(tb_if, mon2scb);
    scb     = new(mon2scb);

    fork
      gen.run();
      drv.run();
      mon.run();
      scb.run();
    join

    $finish;
  end

endmodule
```

## Procedural Values 

```verilog

// Interface
interface full_adder_if;
  logic a, b, cin;
  logic sum, cout;
endinterface


// Transaction
class transaction;
  bit a, b, cin;
  bit sum, cout;

  function new(bit a, bit b, bit cin);
    this.a   = a;
    this.b   = b;
    this.cin = cin;
  endfunction

  function void display(string msg = "");
    $display("%s A=%b B=%b Cin=%b | Sum=%b Cout=%b",
             msg, a, b, cin, sum, cout);
  endfunction
endclass


// Generator
class generator;
  mailbox #(transaction) gen2drv;

  function new(mailbox #(transaction) gen2drv);
    this.gen2drv = gen2drv;
  endfunction

  task run();
    transaction trans;
    for (int i = 0; i < 8; i++) begin
      trans = new(i[2], i[1], i[0]);
      trans.display("GEN:");
      gen2drv.put(trans);
    end
  endtask
endclass


// Driver
class driver;
  virtual full_adder_if  tb_if;
  mailbox #(transaction) gen2drv;

  function new(virtual full_adder_if tb_if_instance,
               mailbox #(transaction) gen2drv);
    this.tb_if   = tb_if_instance;
    this.gen2drv = gen2drv;
  endfunction

  task run();
    transaction trans;
    repeat (8) begin
      gen2drv.get(trans);
      tb_if.a   = trans.a;
      tb_if.b   = trans.b;
      tb_if.cin = trans.cin;
      #10;
    end
  endtask
endclass


// Monitor
class monitor;
  virtual full_adder_if  tb_if;
  mailbox #(transaction) mon2scb;

  function new(virtual full_adder_if tb_if_instance,
               mailbox #(transaction) mon2scb);
    this.tb_if   = tb_if_instance;
    this.mon2scb = mon2scb;
  endfunction

  task run();
    transaction trans;
    repeat (8) begin
      #10;
      #1; // settling delay
      trans      = new(tb_if.a, tb_if.b, tb_if.cin);
      trans.sum  = tb_if.sum;
      trans.cout = tb_if.cout;
      trans.display("MON:");
      mon2scb.put(trans);
    end
  endtask
endclass


// Scoreboard
class scoreboard;
  mailbox #(transaction) mon2scb;

  function new(mailbox #(transaction) mon2scb);
    this.mon2scb = mon2scb;
  endfunction

  task run();
    transaction trans;
    bit         expected_sum, expected_cout;

    repeat (8) begin
      mon2scb.get(trans);

      expected_sum  =  trans.a ^ trans.b ^ trans.cin;
      expected_cout = (trans.a & trans.b)
                    | (trans.b & trans.cin)
                    | (trans.a & trans.cin);

      if (trans.sum !== expected_sum || trans.cout !== expected_cout)
        $display("ERROR  at time %0t: Expected Sum=%b Got Sum=%b | Expected Cout=%b Got Cout=%b",
                 $time, expected_sum, trans.sum, expected_cout, trans.cout);
      else
        $display("PASSED at time %0t: Sum=%b Cout=%b", $time, trans.sum, trans.cout);
    end
  endtask
endclass


// Testbench Top
module full_adder_testbench;

  full_adder_if tb_if();

  full_adder dut (
    .a   (tb_if.a),
    .b   (tb_if.b),
    .cin (tb_if.cin),
    .sum (tb_if.sum),
    .cout(tb_if.cout)
  );

  generator              gen;
  driver                 drv;
  monitor                mon;
  scoreboard             scb;
  mailbox #(transaction) gen2drv;
  mailbox #(transaction) mon2scb;

  initial begin
    $dumpfile("dump.vcd");
    $dumpvars;

    gen2drv = new();
    mon2scb = new();
    gen     = new(gen2drv);
    drv     = new(tb_if, gen2drv);
    mon     = new(tb_if, mon2scb);
    scb     = new(mon2scb);

    fork
      gen.run();
      drv.run();
      mon.run();
      scb.run();
    join

    $finish;
  end

endmodule
```

```verilog
// DUT (Full Adder)
module full_adder (
    input logic a, b, cin,
    output logic sum, cout
);
    assign sum = a ^ b ^ cin; // Sum logic
    assign cout = (a & b) | (b & cin) | (cin & a); // Carry logic
endmodule
```

# Three Mailboxes (week-6)

```verilog
interface full_adder_if;  
  logic a, b, cin;  
  logic sum, cout;  
endinterface  
  
class transaction;  
  rand bit a, b, cin;  
  bit sum, cout;  
  
  function void display(string msg = "");  
    $display("%s A=%b B=%b Cin=%b | Sum=%b Cout=%b",  
             msg, a, b, cin, sum, cout);  
  endfunction  
endclass  
  
  
class generator;  
  mailbox #(transaction) gen2drv;  
   
  function new(mailbox #(transaction) gen2drv);  
    this.gen2drv = gen2drv;  
  endfunction  
  
  task run();  
    transaction trans;  
    repeat (10) begin  
      trans = new();  
      if (!trans.randomize()) $fatal("Gen: Randomization failed");  
      gen2drv.put(trans);  
      trans.display("[ GEN ]");  
      #10;  
    end  
  endtask  
endclass  
  
  
class driver;  
  virtual full_adder_if tb_if;  
  mailbox #(transaction) gen2drv;  
  mailbox #(transaction) drv2scb;  
  
  function new(virtual full_adder_if tb_if,  
               mailbox #(transaction) gen2drv,  
               mailbox #(transaction) drv2scb);  
    this.tb_if = tb_if;  
    this.gen2drv = gen2drv;  
    this.drv2scb = drv2scb;  
  endfunction  
  
  task run();  
    transaction trans;  
    forever begin  
      gen2drv.get(trans);  
      tb_if.a   = trans.a;  
      tb_if.b   = trans.b;  
      tb_if.cin = trans.cin;  
      drv2scb.put(trans);  
      trans.display("[ DRV ]");  
      #10;  
    end  
  endtask  
endclass  
  
  
class monitor;  
  virtual full_adder_if tb_if;  
  mailbox #(transaction) mon2scb;  
  
  function new(virtual full_adder_if tb_if, mailbox #(transaction) mon2scb);  
    this.tb_if = tb_if;  
    this.mon2scb = mon2scb;  
  endfunction  
  
  task run();  
    transaction trans;  
    forever begin  
      trans = new();  
      #5;  
      trans.a    = tb_if.a;  
      trans.b    = tb_if.b;  
      trans.cin  = tb_if.cin;  
      trans.sum  = tb_if.sum;  
      trans.cout = tb_if.cout;  
      mon2scb.put(trans);  
      trans.display("[ MON ]");  
      #5;  
    end  
  endtask  
endclass  
  
  
class scoreboard;  
  mailbox #(transaction) drv2scb;  
  mailbox #(transaction) mon2scb;  
  
  function new(mailbox #(transaction) drv2scb, mailbox #(transaction) mon2scb);  
    this.drv2scb = drv2scb;  
    this.mon2scb = mon2scb;  
  endfunction  
  
  task run();  
    transaction drv_trans;  
    transaction mon_trans;  
    bit expected_sum;  
    bit expected_cout;  
  
    forever begin  
      drv2scb.get(drv_trans);  
      mon2scb.get(mon_trans);  
  
      expected_sum  = drv_trans.a ^ drv_trans.b ^ drv_trans.cin;  
      expected_cout = (drv_trans.a & drv_trans.b) |  
                      (drv_trans.b & drv_trans.cin) |  
                      (drv_trans.a & drv_trans.cin);  
  
      if (mon_trans.sum !== expected_sum || mon_trans.cout !== expected_cout)  
        $display("[ SCB ] ERROR: A=%b B=%b Cin=%b | Exp: S=%b C=%b | Got: S=%b C=%b",  
                 drv_trans.a, drv_trans.b, drv_trans.cin,  
                 expected_sum, expected_cout, mon_trans.sum, mon_trans.cout);  
      else  
        $display("[ SCB ] PASSED: A=%b B=%b Cin=%b | Sum=%b Cout=%b",  
                 drv_trans.a, drv_trans.b, drv_trans.cin, mon_trans.sum, mon_trans.cout);  
    end  
  endtask  
endclass  
  
  
module full_adder_testbench;  
  
  full_adder_if tb_if();  
  
  full_adder dut (  
    .a   (tb_if.a),  
    .b   (tb_if.b),  
    .cin (tb_if.cin),  
    .sum (tb_if.sum),  
    .cout(tb_if.cout)  
  );  
  
  generator  gen;  
  driver     drv;  
  monitor    mon;  
  scoreboard scb;  
  
  mailbox #(transaction) gen2drv;  
  mailbox #(transaction) drv2scb;  
  mailbox #(transaction) mon2scb;  
  
  initial begin  
     
    gen2drv = new();  
    drv2scb = new();  
    mon2scb = new();  
  
    gen = new(gen2drv);  
    drv = new(tb_if, gen2drv, drv2scb);  
    mon = new(tb_if, mon2scb);  
    scb = new(drv2scb, mon2scb);  
  
     
    fork  
      gen.run();  
      drv.run();  
      mon.run();  
      scb.run();  
    join_any  
    $display("Simulation Finished");  
    $finish;  
  end  
  
  initial begin  
    $dumpfile("dump.vcd");  
    $dumpvars;  
  end  
endmodule
```

```verilog
// DUT (Full Adder)
module full_adder (
    input logic a, b, cin,
    output logic sum, cout
);
    assign sum = a ^ b ^ cin; // Sum logic
    assign cout = (a & b) | (b & cin) | (cin & a); // Carry logic
endmodule
```

# Environment class (week-7)

```verilog
interface full_adder_if;  
  logic a, b, cin;
  logic sum, cout;  
endinterface  
  
class transaction;  
  rand bit a, b, cin;  
  bit sum, cout;  
  
  function void display(string msg = "");  
    $display("%s A=%b B=%b Cin=%b | Sum=%b Cout=%b",  
             msg, a, b, cin, sum, cout);  
  endfunction  
endclass  
  
  
class generator;  
  mailbox #(transaction) gen2drv;  
   
  function new(mailbox #(transaction) gen2drv);  
    this.gen2drv = gen2drv;  
  endfunction  
  
  task run();  
    transaction trans;  
    repeat (10) begin  
      trans = new();  
      if (!trans.randomize()) $fatal("Gen: Randomization failed");  
      gen2drv.put(trans);  
      trans.display("[ GEN ]");  
      #10;  
    end  
  endtask  
endclass  
  
  
class driver;  
  virtual full_adder_if tb_if;  
  mailbox #(transaction) gen2drv;  
  mailbox #(transaction) drv2scb;  
  
  function new(virtual full_adder_if tb_if,  
               mailbox #(transaction) gen2drv,  
               mailbox #(transaction) drv2scb);  
    this.tb_if = tb_if;  
    this.gen2drv = gen2drv;  
    this.drv2scb = drv2scb;  
  endfunction  
  
  task run();  
    transaction trans;  
    forever begin  
      gen2drv.get(trans);  
      tb_if.a   = trans.a;  
      tb_if.b   = trans.b;  
      tb_if.cin = trans.cin;  
      drv2scb.put(trans);  
      trans.display("[ DRV ]");  
      #10;  
    end  
  endtask  
endclass  
  
  
class monitor;  
  virtual full_adder_if tb_if;  
  mailbox #(transaction) mon2scb;  
  
  function new(virtual full_adder_if tb_if, mailbox #(transaction) mon2scb);  
    this.tb_if = tb_if;  
    this.mon2scb = mon2scb;  
  endfunction  
  
  task run();  
    transaction trans;  
    forever begin  
      trans = new();  
      #5;  
      trans.a    = tb_if.a;  
      trans.b    = tb_if.b;  
      trans.cin  = tb_if.cin;  
      trans.sum  = tb_if.sum;  
      trans.cout = tb_if.cout;  
      mon2scb.put(trans);  
      trans.display("[ MON ]");  
      #5;  
    end  
  endtask  
endclass  
  
  
class scoreboard;  
  mailbox #(transaction) drv2scb;  
  mailbox #(transaction) mon2scb;  
  
  function new(mailbox #(transaction) drv2scb, mailbox #(transaction) mon2scb);  
    this.drv2scb = drv2scb;  
    this.mon2scb = mon2scb;  
  endfunction  
  
  task run();  
    transaction drv_trans;  
    transaction mon_trans;  
    bit expected_sum;  
    bit expected_cout;  
  
    forever begin  
      drv2scb.get(drv_trans);  
      mon2scb.get(mon_trans);  
  
      expected_sum  = drv_trans.a ^ drv_trans.b ^ drv_trans.cin;  
      expected_cout = (drv_trans.a & drv_trans.b) |  
                      (drv_trans.b & drv_trans.cin) |  
                      (drv_trans.a & drv_trans.cin);  
  
      if (mon_trans.sum !== expected_sum || mon_trans.cout !== expected_cout)  
        $display("[ SCB ] ERROR: A=%b B=%b Cin=%b | Exp: S=%b C=%b | Got: S=%b C=%b",  
                 drv_trans.a, drv_trans.b, drv_trans.cin,  
                 expected_sum, expected_cout, mon_trans.sum, mon_trans.cout);  
      else  
        $display("[ SCB ] PASSED: A=%b B=%b Cin=%b | Sum=%b Cout=%b",  
                 drv_trans.a, drv_trans.b, drv_trans.cin, mon_trans.sum, mon_trans.cout);  
    end  
  endtask  
endclass  


class environment;
  generator  gen;
  driver     drv;
  monitor    mon;
  scoreboard scb;

  mailbox #(transaction) gen2drv;
  mailbox #(transaction) drv2scb;
  mailbox #(transaction) mon2scb;

  function new(virtual full_adder_if tb_if);
    gen2drv = new();
    drv2scb = new();
    mon2scb = new();

    gen = new(gen2drv);
    drv = new(tb_if, gen2drv, drv2scb);
    mon = new(tb_if, mon2scb);
    scb = new(drv2scb, mon2scb);
  endfunction

  task run();
    fork
      gen.run();
      drv.run();
      mon.run();
      scb.run();
    join_any
    $display("Simulation Finished");
    $finish;
  endtask
endclass
  
  
module full_adder_testbench;  
  
  full_adder_if tb_if();  
  
  full_adder dut (  
    .a   (tb_if.a),  
    .b   (tb_if.b),  
    .cin (tb_if.cin),  
    .sum (tb_if.sum),  
    .cout(tb_if.cout)  
  );  

  environment env;
  
  initial begin  
    env = new(tb_if);
    env.run();
  end  
  
  initial begin  
    $dumpfile("dump.vcd");  
    $dumpvars;  
  end  
endmodule
```

```verilog
// DUT (Full Adder)
module full_adder (
    input logic a, b, cin,
    output logic sum, cout
);
    assign sum = a ^ b ^ cin; // Sum logic
    assign cout = (a & b) | (b & cin) | (cin & a); // Carry logic
endmodule
```

# Agent 

```verilog
interface full_adder_if;
  logic a, b, cin;
  logic sum, cout;
endinterface


class transaction;
  rand bit a, b, cin;
  bit sum, cout;

  function void display(string msg = "");
    $display("%s A=%b B=%b Cin=%b | Sum=%b Cout=%b",
             msg, a, b, cin, sum, cout);
  endfunction
endclass


class generator;
  mailbox #(transaction) gen2drv0;
  mailbox #(transaction) gen2drv1;

  function new(mailbox #(transaction) gen2drv0,
               mailbox #(transaction) gen2drv1);
    this.gen2drv0 = gen2drv0;
    this.gen2drv1 = gen2drv1;
  endfunction

  task run();
    transaction trans0, trans1;
    repeat (10) begin
      trans0 = new();
      if (!trans0.randomize()) $fatal("Gen: Randomization failed");

      // deep copy into trans1 so both agents get identical stimulus
      trans1 = new();
      trans1.a   = trans0.a;
      trans1.b   = trans0.b;
      trans1.cin = trans0.cin;

      gen2drv0.put(trans0);
      gen2drv1.put(trans1);
      trans0.display("[ GEN ]");
      #10;
    end
  endtask
endclass


class driver;
  virtual full_adder_if tb_if;
  mailbox #(transaction) gen2drv;
  mailbox #(transaction) drv2scb;

  function new(virtual full_adder_if tb_if,
               mailbox #(transaction) gen2drv,
               mailbox #(transaction) drv2scb);
    this.tb_if   = tb_if;
    this.gen2drv = gen2drv;
    this.drv2scb = drv2scb;
  endfunction

  task run();
    transaction trans;
    forever begin
      gen2drv.get(trans);
      tb_if.a   = trans.a;
      tb_if.b   = trans.b;
      tb_if.cin = trans.cin;
      drv2scb.put(trans);
      trans.display("[ DRV ]");
      #10;
    end
  endtask
endclass


class monitor;
  virtual full_adder_if tb_if;
  mailbox #(transaction) mon2scb;

  function new(virtual full_adder_if tb_if,
               mailbox #(transaction) mon2scb);
    this.tb_if   = tb_if;
    this.mon2scb = mon2scb;
  endfunction

  task run();
    transaction trans;
    forever begin
      trans = new();
      #5;
      trans.a    = tb_if.a;
      trans.b    = tb_if.b;
      trans.cin  = tb_if.cin;
      trans.sum  = tb_if.sum;
      trans.cout = tb_if.cout;
      mon2scb.put(trans);
      trans.display("[ MON ]");
      #5;
    end
  endtask
endclass


class agent;
  driver  drv;
  monitor mon;

  mailbox #(transaction) gen2drv;
  mailbox #(transaction) drv2scb;
  mailbox #(transaction) mon2scb;

  function new(virtual full_adder_if tb_if,
               mailbox #(transaction) gen2drv,
               mailbox #(transaction) drv2scb,
               mailbox #(transaction) mon2scb);
    this.gen2drv = gen2drv;
    this.drv2scb = drv2scb;
    this.mon2scb = mon2scb;

    drv = new(tb_if, gen2drv, drv2scb);
    mon = new(tb_if, mon2scb);
  endfunction

  task run();
    fork
      drv.run();
      mon.run();
    join_any
  endtask
endclass


class scoreboard;
  mailbox #(transaction) drv2scb0;
  mailbox #(transaction) mon2scb0;
  mailbox #(transaction) drv2scb1;
  mailbox #(transaction) mon2scb1;

  function new(mailbox #(transaction) drv2scb0,
               mailbox #(transaction) mon2scb0,
               mailbox #(transaction) drv2scb1,
               mailbox #(transaction) mon2scb1);
    this.drv2scb0 = drv2scb0;
    this.mon2scb0 = mon2scb0;
    this.drv2scb1 = drv2scb1;
    this.mon2scb1 = mon2scb1;
  endfunction

  task check(mailbox #(transaction) drv2scb,
             mailbox #(transaction) mon2scb,
             string tag);
    transaction drv_trans, mon_trans;
    bit expected_sum, expected_cout;
    forever begin
      drv2scb.get(drv_trans);
      mon2scb.get(mon_trans);

      expected_sum  = drv_trans.a ^ drv_trans.b ^ drv_trans.cin;
      expected_cout = (drv_trans.a & drv_trans.b) |
                      (drv_trans.b & drv_trans.cin) |
                      (drv_trans.a & drv_trans.cin);

      if (mon_trans.sum !== expected_sum || mon_trans.cout !== expected_cout)
        $display("[ SCB ][ %s ] ERROR: A=%b B=%b Cin=%b | Exp: S=%b C=%b | Got: S=%b C=%b",
                 tag, drv_trans.a, drv_trans.b, drv_trans.cin,
                 expected_sum, expected_cout, mon_trans.sum, mon_trans.cout);
      else
        $display("[ SCB ][ %s ] PASSED: A=%b B=%b Cin=%b | Sum=%b Cout=%b",
                 tag, drv_trans.a, drv_trans.b, drv_trans.cin,
                 mon_trans.sum, mon_trans.cout);
    end
  endtask

  task run();
    fork
      check(drv2scb0, mon2scb0, "DUT0");
      check(drv2scb1, mon2scb1, "DUT1");
    join_any
  endtask
endclass


class environment;
  generator  gen;
  agent      agt0;
  agent      agt1;
  scoreboard scb;

  mailbox #(transaction) gen2drv0;
  mailbox #(transaction) gen2drv1;
  mailbox #(transaction) drv2scb0;
  mailbox #(transaction) mon2scb0;
  mailbox #(transaction) drv2scb1;
  mailbox #(transaction) mon2scb1;

  function new(virtual full_adder_if tb_if0,
               virtual full_adder_if tb_if1);
    gen2drv0 = new();
    gen2drv1 = new();
    drv2scb0 = new();
    mon2scb0 = new();
    drv2scb1 = new();
    mon2scb1 = new();

    gen  = new(gen2drv0, gen2drv1);
    agt0 = new(tb_if0, gen2drv0, drv2scb0, mon2scb0);
    agt1 = new(tb_if1, gen2drv1, drv2scb1, mon2scb1);
    scb  = new(drv2scb0, mon2scb0, drv2scb1, mon2scb1);
  endfunction

  task run();
    fork
      gen.run();
      agt0.run();
      agt1.run();
      scb.run();
    join_any
    $display("Simulation Finished");
    $finish;
  endtask
endclass


module full_adder_testbench;

  full_adder_if tb_if0();
  full_adder_if tb_if1();

  full_adder dut0 (
    .a   (tb_if0.a),
    .b   (tb_if0.b),
    .cin (tb_if0.cin),
    .sum (tb_if0.sum),
    .cout(tb_if0.cout)
  );

  full_adder dut1 (
    .a   (tb_if1.a),
    .b   (tb_if1.b),
    .cin (tb_if1.cin),
    .sum (tb_if1.sum),
    .cout(tb_if1.cout)
  );

  environment env;

  initial begin
    env = new(tb_if0, tb_if1);
    env.run();
  end

  initial begin
    $dumpfile("dump.vcd");
    $dumpvars;
  end

endmodule
```

```verilog
// DUT (Full Adder)
module full_adder (
    input logic a, b, cin,
    output logic sum, cout
);
    assign sum = a ^ b ^ cin; // Sum logic
    assign cout = (a & b) | (b & cin) | (cin & a); // Carry logic
endmodule
```

# Agent with is_active and Cover Groups (week - 8)

```verilog
`timescale 1ns/1ps

// ─────────────────────────────────────────────
// Interface
// ─────────────────────────────────────────────

interface full_adder_if;
  logic a, b, cin;
  logic sum, cout;

  always_comb begin
    assert (sum == (a ^ b ^ cin))
      else $error("ASSERT FAIL [SUM]  a=%b b=%b cin=%b | got sum=%b", a, b, cin, sum);

    assert (cout == ((a & b) | (b & cin) | (a & cin)))
      else $error("ASSERT FAIL [COUT] a=%b b=%b cin=%b | got cout=%b", a, b, cin, cout);
  end
endinterface


// ─────────────────────────────────────────────
// Transaction
// ─────────────────────────────────────────────

class transaction;
  rand bit a, b, cin;
  bit sum, cout;

  function void display(string tag = "");
    $display("%-15s a=%b b=%b cin=%b | sum=%b cout=%b",
             tag, a, b, cin, sum, cout);
  endfunction
endclass


// ─────────────────────────────────────────────
// Generator
// ─────────────────────────────────────────────

class generator;
  mailbox #(transaction) gen2drv;
  int mode; // 0 = random, 1 = directed

  function new(mailbox #(transaction) gen2drv, int mode = 0);
    this.gen2drv = gen2drv;
    this.mode    = mode;
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
      // exhaustive: all 8 input combinations
      for (int i = 0; i < 8; i++) begin
        t     = new();
        t.a   = i[2];
        t.b   = i[1];
        t.cin = i[0];
        t.display("[GEN-DIRECTED]");
        gen2drv.put(t);
        #10;
      end
    end
  endtask
endclass


// ─────────────────────────────────────────────
// Driver
// ─────────────────────────────────────────────

class driver;
  virtual full_adder_if tb_if;
  mailbox #(transaction) gen2drv;
  mailbox #(transaction) drv2scb;

  function new(virtual full_adder_if tb_if,
               mailbox #(transaction) gen2drv,
               mailbox #(transaction) drv2scb);
    this.tb_if   = tb_if;
    this.gen2drv = gen2drv;
    this.drv2scb = drv2scb;
  endfunction

  task run();
    transaction t;
    forever begin
      gen2drv.get(t);

      tb_if.a   = t.a;
      tb_if.b   = t.b;
      tb_if.cin = t.cin;

      t.display("[DRV]");
      drv2scb.put(t);
      #10;
    end
  endtask
endclass


// ─────────────────────────────────────────────
// Monitor  (with functional coverage)
// ─────────────────────────────────────────────

class monitor;
  virtual full_adder_if tb_if;
  mailbox #(transaction) mon2scb;

  covergroup input_coverage;
    option.per_instance = 1;

    cp_a   : coverpoint tb_if.a;
    cp_b   : coverpoint tb_if.b;
    cp_cin : coverpoint tb_if.cin;

    // ensures all 8 input combos are seen
    cross_abc : cross cp_a, cp_b, cp_cin;
  endgroup

  function new(virtual full_adder_if tb_if,
               mailbox #(transaction) mon2scb);
    this.tb_if   = tb_if;
    this.mon2scb = mon2scb;
    input_coverage = new();
  endfunction

  task run();
    transaction t;
    forever begin
      t = new();

      // sample at t=9 — just before next stimulus lands at t=10
      // gives combinational logic maximum settling time
      #9;
      t.a    = tb_if.a;
      t.b    = tb_if.b;
      t.cin  = tb_if.cin;
      t.sum  = tb_if.sum;
      t.cout = tb_if.cout;

      input_coverage.sample();
      mon2scb.put(t);
      t.display("[MON]");
      #1;
    end
  endtask
endclass


// ─────────────────────────────────────────────
// Scoreboard
// ─────────────────────────────────────────────

class scoreboard;
  mailbox #(transaction) drv2scb;
  mailbox #(transaction) mon2scb;

  function new(mailbox #(transaction) drv2scb,
               mailbox #(transaction) mon2scb);
    this.drv2scb = drv2scb;
    this.mon2scb = mon2scb;
  endfunction

  task run();
    transaction from_drv, from_mon;
    bit expected_sum, expected_cout;

    forever begin
      drv2scb.get(from_drv);
      mon2scb.get(from_mon);

      expected_sum  = from_drv.a ^ from_drv.b ^ from_drv.cin;
      expected_cout = (from_drv.a & from_drv.b) |
                      (from_drv.b & from_drv.cin) |
                      (from_drv.a & from_drv.cin);

      if (from_mon.sum !== expected_sum || from_mon.cout !== expected_cout)
        $display("[ SCB ] FAIL  a=%b b=%b cin=%b | exp: sum=%b cout=%b | got: sum=%b cout=%b",
                 from_drv.a, from_drv.b, from_drv.cin,
                 expected_sum, expected_cout,
                 from_mon.sum, from_mon.cout);
      else
        $display("[ SCB ] PASS  a=%b b=%b cin=%b | sum=%b cout=%b",
                 from_drv.a, from_drv.b, from_drv.cin,
                 from_mon.sum, from_mon.cout);
    end
  endtask
endclass


// ─────────────────────────────────────────────
// Agent  (active = drives + monitors, passive = monitors only)
// ─────────────────────────────────────────────

class agent;
  driver  drv;
  monitor mon;
  bit     is_active;

  function new(virtual full_adder_if tb_if,
               mailbox #(transaction) gen2drv,
               mailbox #(transaction) drv2scb,
               mailbox #(transaction) mon2scb,
               bit is_active);
    this.is_active = is_active;

    if (is_active)
      drv = new(tb_if, gen2drv, drv2scb);

    mon = new(tb_if, mon2scb);
  endfunction

  task run();
    if (is_active) begin
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


// ─────────────────────────────────────────────
// Environment
// ─────────────────────────────────────────────

class environment;
  generator  gen;
  agent      active_agent;
  agent      passive_agent;
  scoreboard scb;

  mailbox #(transaction) gen2drv;
  mailbox #(transaction) drv2scb;
  mailbox #(transaction) mon2scb;

  function new(virtual full_adder_if tb_if, int mode);
    gen2drv = new();
    drv2scb = new();
    mon2scb = new();

    gen          = new(gen2drv, mode);
    active_agent = new(tb_if, gen2drv, drv2scb, mon2scb, 1);
    passive_agent = new(tb_if, null, null, mon2scb, 0);
    scb          = new(drv2scb, mon2scb);
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


// ─────────────────────────────────────────────
// Base Test
// ─────────────────────────────────────────────

class base_test;
  environment          env;
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


// ─────────────────────────────────────────────
// Top-level Testbench Module
// ─────────────────────────────────────────────

module testbench;

  full_adder_if tb_if();

  full_adder dut (
    .a   (tb_if.a),
    .b   (tb_if.b),
    .cin (tb_if.cin),
    .sum (tb_if.sum),
    .cout(tb_if.cout)
  );

  directed_test t;  // swap to random_test for randomized run

  initial begin
    $dumpfile("dump.vcd");
    $dumpvars;

    t = new(tb_if);
    t.run();
  end

endmodule
```

## raw code from prof

```verilog
`timescale 1ns/1ps

interface full_adder_if;
  logic a, b, cin;
  logic sum, cout;

 always_comb begin
    assert (sum == (a ^ b ^ cin))
      else $error("ASSERT FAIL: SUM incorrect a=%b b=%b cin=%b", a,b,cin);

    assert (cout == ((a & b) | (b & cin) | (a & cin)))
      else $error("ASSERT FAIL: COUT incorrect a=%b b=%b cin=%b", a,b,cin);
  end

endinterface

class transaction;
  rand bit a, b, cin;
  bit sum, cout;

  function void display(string msg="");
    $display("%s A=%b B=%b Cin=%b | Sum=%b Cout=%b",
             msg, a, b, cin, sum, cout);
  endfunction
endclass

class generator;
  mailbox #(transaction) gen2drv;
  int mode; // 0=random, 1=directed

  function new(mailbox #(transaction) gen2drv, int mode=0);
    this.gen2drv = gen2drv;
    this.mode = mode;
  endfunction

  task generate_stim();
    transaction t;

    if (mode == 0) begin
      repeat(10) begin
        t = new();
        assert(t.randomize());
        t.display("[GEN-RANDOM]");
        gen2drv.put(t);
        #10;
      end
    end
    else begin
      for (int i=0; i<8; i++) begin
        t = new();
        t.a   = i[2];
        t.b   = i[1];
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

  function new(
    virtual full_adder_if tb_if,
    mailbox #(transaction) gen2drv,
    mailbox #(transaction) drv2scb
  );
    this.tb_if = tb_if;
    this.gen2drv = gen2drv;
    this.drv2scb = drv2scb;
  endfunction

  task drive();
    transaction t;
    forever begin
      gen2drv.get(t);

      tb_if.a   = t.a;
      tb_if.b   = t.b;
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

  covergroup fa_cg;

    option.per_instance = 1;

    A_cp   : coverpoint tb_if.a;
    B_cp   : coverpoint tb_if.b;
    CIN_cp : coverpoint tb_if.cin;

    ABC_cross : cross A_cp, B_cp, CIN_cp;

  endgroup

  // Handle declaration
  fa_cg cg;

  function new(
    virtual full_adder_if tb_if,
    mailbox #(transaction) mon2scb
  );
    this.tb_if = tb_if;
    this.mon2scb = mon2scb;

    cg = new(); // IMPORTANT
  endfunction

  task observe();
    transaction t;

    forever begin
      t = new();

      #9;
      t.a    = tb_if.a;
      t.b    = tb_if.b;
      t.cin  = tb_if.cin;
      t.sum  = tb_if.sum;
      t.cout = tb_if.cout;

      cg.sample(); // manual sampling

      mon2scb.put(t);

      #1;
    end
  endtask

endclass

class scoreboard;

  mailbox #(transaction) drv2scb;
  mailbox #(transaction) mon2scb;

  function new(
    mailbox #(transaction) drv2scb,
    mailbox #(transaction) mon2scb
  );
    this.drv2scb = drv2scb;
    this.mon2scb = mon2scb;
  endfunction

  task check();
    transaction d, m;
    bit s, c;

    forever begin
      drv2scb.get(d);
      mon2scb.get(m);

      s = d.a ^ d.b ^ d.cin;
      c = (d.a & d.b) | (d.b & d.cin) | (d.a & d.cin);

      if (m.sum !== s || m.cout !== c)
        $display("ERROR: A=%b B=%b Cin=%b", d.a, d.b, d.cin);
      else
        $display("PASS");
    end
  endtask
endclass


class agent;

  driver drv;
  monitor mon;
  bit is_active;

  function new(
    virtual full_adder_if tb_if,
    mailbox #(transaction) gen2drv,
    mailbox #(transaction) drv2scb,
    mailbox #(transaction) mon2scb,
    bit is_active
  );

    this.is_active = is_active;

    if (is_active)
      drv = new(tb_if, gen2drv, drv2scb);

    mon = new(tb_if, mon2scb);

  endfunction

  task run();
    if (is_active) begin
      fork
        drv.drive();
        mon.observe();
      join_none
    end
    else begin
      mon.observe();
    end
  endtask

endclass


class environment;

  generator gen;
  agent active_agt, passive_agt;
  scoreboard scb;

  mailbox #(transaction) gen2drv;
  mailbox #(transaction) drv2scb;
  mailbox #(transaction) mon2scb;

  int mode;

  function new(virtual full_adder_if tb_if, int mode);

    this.mode = mode;

    gen2drv = new();
    drv2scb = new();
    mon2scb = new();

    gen = new(gen2drv, mode);

    active_agt  = new(tb_if, gen2drv, drv2scb, mon2scb, 1);
    passive_agt = new(tb_if, null, null, mon2scb, 0);

    scb = new(drv2scb, mon2scb);

  endfunction

  task run();
    fork
      gen.generate_stim();
      active_agt.run();
      passive_agt.run();
      scb.check();
    join_none
  endtask

endclass

class base_test;

  environment env;
  virtual full_adder_if tb_if;
  int mode;

  function new(virtual full_adder_if tb_if, int mode);
    this.tb_if = tb_if;
    this.mode = mode;
    env = new(tb_if, mode);
  endfunction

  task run();
    env.run();
    #200 $finish;
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

  full_adder dut(
    .a(tb_if.a),
    .b(tb_if.b),
    .cin(tb_if.cin),
    .sum(tb_if.sum),
    .cout(tb_if.cout)
  );

  directed_test t; 

  initial begin
    $dumpfile("dump.vcd");
    $dumpvars;

    t = new(tb_if);
    t.run();
  end

endmodule
```

```verilog
// DUT (Full Adder)
module full_adder (
    input logic a, b, cin,
    output logic sum, cout
);
    assign sum = a ^ b ^ cin; // Sum logic
    assign cout = (a & b) | (b & cin) | (cin & a); // Carry logic
endmodule
```

