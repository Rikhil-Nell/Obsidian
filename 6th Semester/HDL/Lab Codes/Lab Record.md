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