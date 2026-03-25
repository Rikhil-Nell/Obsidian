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