
## **1️⃣ Full Adder Using Half Adders**

```verilog
// DESIGN
module half_adder(input a,b, output s,c);
  assign s=a^b, c=a&b;
endmodule

module full_adder(input a,b,cin, output s,cout);
  wire s1,c1,c2;
  half_adder h1(a,b,s1,c1);
  half_adder h2(s1,cin,s,c2);
  assign cout=c1|c2;
endmodule

// TESTBENCH
module tb_full_adder;
  reg a,b,cin; wire s,cout;
  full_adder dut(a,b,cin,s,cout);
  initial begin
    $monitor("a=%b b=%b cin=%b -> s=%b cout=%b",a,b,cin,s,cout);
    a=0;b=0;cin=0;#10;
    a=0;b=1;cin=1;#10;
    a=1;b=1;cin=0;#10;
    a=1;b=1;cin=1;#10;
    $finish;
  end
endmodule
```

---

## **2️⃣ 3×8 Decoder**

```verilog
// DESIGN
module decoder3x8(input [2:0] a, output [7:0] y);
  assign y = 1 << a;
endmodule

// TESTBENCH
module tb_decoder3x8;
  reg [2:0] a; wire [7:0] y;
  decoder3x8 dut(a,y);
  initial begin
    $monitor("a=%b -> y=%b",a,y);
    a=0;#5;a=1;#5;a=2;#5;a=7;#5;
    $finish;
  end
endmodule
```

---

## **3️⃣ 4-Bit Full Adder**

```verilog
// DESIGN
module fa1(input a,b,cin, output s,cout);
  assign {cout,s}=a+b+cin;
endmodule

module fa4(input [3:0] a,b, input cin, output [3:0] s, output cout);
  wire [2:0] c;
  fa1 f0(a[0],b[0],cin,s[0],c[0]);
  fa1 f1(a[1],b[1],c[0],s[1],c[1]);
  fa1 f2(a[2],b[2],c[1],s[2],c[2]);
  fa1 f3(a[3],b[3],c[2],s[3],cout);
endmodule

// TESTBENCH
module tb_fa4;
  reg [3:0] a,b; reg cin; wire [3:0] s; wire cout;
  fa4 dut(a,b,cin,s,cout);
  initial begin
    $monitor("a=%b b=%b cin=%b -> sum=%b cout=%b",a,b,cin,s,cout);
    a=4'b0101;b=4'b0011;cin=0;#10;
    a=4'b1111;b=4'b0001;cin=1;#10;
    a=4'b1010;b=4'b0101;cin=0;#10;
    $finish;
  end
endmodule
```

---

## **4️⃣ 4-Bit Up Counter**

```verilog
// DESIGN
module upcount(input clk,rst, output reg [3:0] q);
  always@(posedge clk) q <= rst ? 0 : q + 1;
endmodule

// TESTBENCH
module tb_upcount;
  reg clk=0,rst; wire [3:0] q;
  upcount dut(clk,rst,q);
  always #5 clk=~clk;
  initial begin
    $monitor("rst=%b q=%b",rst,q);
    rst=1;#10;rst=0;#80;rst=1;#10;rst=0;#40;
    $finish;
  end
endmodule
```

---

## **5️⃣ 4-Bit Carry Look-Ahead Adder**

```verilog
// DESIGN
module cla4(input [3:0] A,B, input Cin, output [3:0] S, output Cout);
  wire [3:0] P,G; wire [4:0] C;
  assign P=A^B; assign G=A&B; C[0]=Cin;
  assign C[1]=G[0]|(P[0]&C[0]);
  assign C[2]=G[1]|(P[1]&C[1]);
  assign C[3]=G[2]|(P[2]&C[2]);
  assign C[4]=G[3]|(P[3]&C[3]);
  assign S=P^C[3:0]; assign Cout=C[4];
endmodule

// TESTBENCH
module tb_cla4;
  reg [3:0] A,B; reg Cin; wire [3:0] S; wire Cout;
  cla4 dut(A,B,Cin,S,Cout);
  initial begin
    $monitor("A=%b B=%b Cin=%b -> S=%b Cout=%b",A,B,Cin,S,Cout);
    A=4'b0111;B=4'b0101;Cin=0;#10;
    A=4'b1111;B=4'b0001;Cin=0;#10;
    A=4'b1010;B=4'b1010;Cin=1;#10;
    $finish;
  end
endmodule
```

---

## **6️⃣ Mealy Machine (Sequence 101)**

```verilog
// DESIGN
module mealy_101(input clk,rst,x, output reg y);
  reg [1:0] st,nxt;
  localparam S0=0,S1=1,S2=2;
  always@(posedge clk,posedge rst) st<=rst?S0:nxt;
  always@(*) begin
    y=0;
    case(st)
      S0: nxt=x?S1:S0;
      S1: nxt=x?S1:S2;
      S2: begin nxt=x?S1:S0; y=x; end
    endcase
  end
endmodule

// TESTBENCH
module tb_mealy_101;
  reg clk=0,rst,x; wire y;
  mealy_101 dut(clk,rst,x,y);
  always #5 clk=~clk;
  initial begin
    $monitor("x=%b y=%b",x,y);
    rst=1;x=0;#10;rst=0;
    x=1;#10;x=0;#10;x=1;#10;x=0;#10;x=1;#10;
    $finish;
  end
endmodule
```

---

## **7️⃣ Synchronous FIFO**

```verilog
// DESIGN
module fifo #(parameter N=8, D=8)
(input clk,rst,we,re,input [N-1:0] din,output [N-1:0] dout,output full,empty);
  reg [N-1:0] mem[D-1:0]; reg [$clog2(D):0] w=0,r=0,c=0;
  assign full=c==D, empty=c==0, dout=mem[r];
  always@(posedge clk) if(rst) begin w<=0;r<=0;c<=0;end
    else begin
      if(we&&!full) begin mem[w]<=din; w<=w+1; c<=c+1; end
      if(re&&!empty) begin r<=r+1; c<=c-1; end
    end
endmodule

// TESTBENCH
module tb_fifo;
  reg clk=0,rst,we,re; reg [7:0] din; wire [7:0] dout; wire full,empty;
  fifo dut(clk,rst,we,re,din,dout,full,empty);
  always #5 clk=~clk;
  initial begin
    rst=1;#10;rst=0;din=8'hA1;we=1;#10;
    din=8'hB2;#10;we=0;re=1;#10;re=0;
    $monitor("we=%b re=%b dout=%h full=%b empty=%b",we,re,dout,full,empty);
    $finish;
  end
endmodule
```

---

## **8️⃣ Custom Peripheral**

```verilog
// DESIGN
module custom_periph(input clk,rst,cs,we,input [1:0] addr,input [31:0] din,output reg [31:0] dout);
  reg [31:0] R[0:3];
  always@(posedge clk or posedge rst)
    if(rst) R[0]<=0; else if(cs && we) R[addr]<=din;
  always@(*) dout=(cs&&!we)?R[addr]:32'hzzzz_zzzz;
endmodule

// TESTBENCH
module tb_custom_periph;
  reg clk=0,rst,cs,we; reg [1:0] addr; reg [31:0] din; wire [31:0] dout;
  custom_periph dut(clk,rst,cs,we,addr,din,dout);
  always #5 clk=~clk;
  initial begin
    rst=1;cs=0;we=0;#10;rst=0;
    cs=1;we=1;addr=2'b00;din=32'hA5A5;#10;
    we=0;addr=2'b00;#10;
    $monitor("addr=%b we=%b dout=%h",addr,we,dout);
    $finish;
  end
endmodule
```

---

## **9️⃣ Synchronous RAM**

```verilog
// DESIGN
module sram(input clk,we,input [5:0] addr,input [7:0] din,output [7:0] dout);
  reg [7:0] mem[0:63]; reg [5:0] a;
  always@(posedge clk) begin
    if(we) mem[addr]<=din; else a<=addr;
  end
  assign dout=mem[a];
endmodule

// TESTBENCH
module tb_sram;
  reg clk=0,we; reg [5:0] addr; reg [7:0] din; wire [7:0] dout;
  sram dut(clk,we,addr,din,dout);
  always #5 clk=~clk;
  initial begin
    we=1;addr=0;din=8'hAA;#10;
    addr=1;din=8'hBB;#10;we=0;addr=0;#10;addr=1;#10;
    $monitor("addr=%d we=%b dout=%h",addr,we,dout);
    $finish;
  end
endmodule
```

---

## **🔟 Memory Unit**

```verilog
// DESIGN
module mem_unit(input clk,rst,cs,we,input [7:0] addr,input [31:0] din,output reg [31:0] dout);
  reg [31:0] mem[0:255];
  always@(posedge clk)
    if(rst) dout<=0;
    else if(cs) dout<=we?(mem[addr]<=din,32'bz):mem[addr];
endmodule

// TESTBENCH
module tb_mem_unit;
  reg clk=0,rst,cs,we; reg [7:0] addr; reg [31:0] din; wire [31:0] dout;
  mem_unit dut(clk,rst,cs,we,addr,din,dout);
  always #5 clk=~clk;
  initial begin
    rst=1;#10;rst=0;
    cs=1;we=1;addr=8'h00;din=32'hDEAD;#10;
    we=0;#10;
    $monitor("addr=%h we=%b dout=%h",addr,we,dout);
    $finish;
  end
endmodule
```

---

## **1️⃣1️⃣ 4-Bit Adder-Subtractor**

```verilog
// DESIGN
module addsub4(input m,input [3:0] a,b,output [4:0] res);
  wire [3:0] xb; assign xb=b^{4{m}};
  assign {res[4],res[3:0]}=a+xb+m;
endmodule

// TESTBENCH
module tb_addsub4;
  reg [3:0] a,b; reg m; wire [4:0] res;
  addsub4 dut(m,a,b,res);
  initial begin
    $monitor("m=%b a=%b b=%b -> res=%b",m,a,b,res);
    m=0;a=4'd9;b=4'd3;#10;
    m=1;a=4'd9;b=4'd3;#10;
    a=4'd5;b=4'd7;#10;
    $finish;
  end
endmodule
```
