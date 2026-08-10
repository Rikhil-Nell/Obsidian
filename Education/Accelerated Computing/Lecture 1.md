---
tags:
  - "#AcceleratedComputing"
  - "#SelfTaught"
---

# Matrix Multiplication

A macbook with apple M2, has about 20 teraflops of compute power. But when we write some python code for the purpose of multiplying two matrices it utilizes about **10 MegaFlops**. that is very very poor performance and not touching anywhere near what the SoC is capable of.

Even in C with all the type optimizations we get to 1 gigaflops. allowing autovectorization we get 20 gigaflops, handtunes can push it to 40 gigflops, some more parallelizing gives 200 gigaflops.

we can push further using 16 bit floats and get using library code apple made and get to 1.5 teraflops.

We can write some metal compute shaders and try to use the gpu.. but even with the matrix accelerators we get around 3.2 teraflops

using a 16 core npu which is inside the m2 chip, we need int16 but we get 16teraOps, with a proprietary ISA.

but because of the nature of how we approached this code, it leaves a flaw that is, this cannot be reused for any other hardware configuration

