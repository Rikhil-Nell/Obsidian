---
"tags:": "#PhysicalChemistry"
---

# The Perfect Gas

A gas is a form of matter that fills whatever container it occupies.

## Variables of  state

The physical state of a sample of a substance, its physical condition is defined by its physical properties. So two objects with same properties would be in the same state.
The variables needed to specify the state of the system are the amount of substance it contains **n**, the volume it occupies **V**, the pressure **p**, and the temperature **t**.

### Pressure

The force exerted by the continuous collisions by the numerous little particles cause the impact to stabilize into a very steady force. This force concentrated on an area is called pressure. SI unit is **pascal ($Pa = 1 Nm^{-2}$)**
A pressure of one bar is the standard pressure for reporting data.
![[Pasted image 20231006151424.png]]

If a container with a moveable wall is filled with low pressure air on one side and air with higher pressure on the other side, after a while the moveable wall will stop moving as the high pressure air distributes itself over an area causing its pressure to drop, this state is called **mechanical equilibrium**.

### Temperature

The degree of hotness or coldness is called temperature, it has 3 scales **Celsius**, **Fahrenheit** and **Kelvin**. **Kelvin** is the Si unit of temperature.
You can convert each of the scales into the other by using the following formula:
$$\frac{C}{100} = \frac{F - 32}{180} = \frac{K - 273}{100}$$
Back in the day, the Celsius scale came about from the expansion of liquid, but as different liquids differently over different ranges of temperatures this led to inaccuracies.

The pressure of gas however can be used to construct a **perfect-gas temperature scale** or **thermodynamic temperature scale**.
On the thermodynamic temperature scale, temperatures are denoted by **T** and are normally reported in kelvins (K, not $^{\circ}K$). Thermodynamic and Celsius scale are related by the exact expression 
$$\frac{T}{K} = \frac{\theta}{^{\circ}C} + 273.15$$
## Equations of state

Since we know that a pure substance can be specified by the values of **n, V, p and T** We required only 3 to find the 4th one and the general equation that interrelates these 4 variables is:
$$p = f(T,V,n)$$

One very important example is the equation of state of a 'perfect gas', which has the form $p = nRT/V$, where R is constant independent of the identity of the gas.

### The empirical basis

**Boyle's Law:**
$pV = constant$, at constant n, T

**Charles' Law:**
$V = constant*T$, at constant n, p
$p =  constant*T$, at constant n, V

**Avogadro's principle:**
$V = constant*n$, at constant p, T

Boyle's and Charles's law are examples of a **limiting law**, a law that is strictly true only in a certain limit, in this case $p \rightarrow 0$.

The empirical observations summarized can be combined into a single expression:$$pV = constant*nT$$
The constant of proportionality which is found experimentally to be the same for all gases, is denoted by **R** and call the **gas constant**. The resulting expression$$pV = nRT$$
is the **perfect gas law**. It is the approximate equation of state of any gas, and becomes increasingly exact as pressure of the gas approaches 0.
A gas that obeys the gas law exactly under all conditions is called a **perfect gas** t.
A **real gas** behaves more like a **perfect gas** the lower the pressure.

While the perfect gas law is of considerable importance for its utility of deriving of a vast number of other realtions throughout thermodynamics, it can also be practically used for calculating the properties of a gas under a variety of conditons. For instance, the molar volume $V_{m}=V/n$, of a perfect gas under **standard ambient temperature and pressure (SATP)**, which means 298.15K and 1 bar (i.e exactly $10^{5} Pa$), is easily calculated from, $V_{m} = RT/p$ to be $24.789    dm^{3}mol^{-1}$  

### Mixture of gases

When dealing with gaseous mixtures, it is often necessary to know the contribution that each component makes to the total pressure of the sample. 
The **partial pressure** , $p_j$ of a gas J in a mixture is defined as$$p_{j}= x_jp$$
where $x_j$ is the **mole fraction** of the component J, the amount of J expressed as a fraction of the total amount of molecules, n, in the sample:$$x_{1}= \frac{n_1}{n}$$
 where, $n = n_{a}+n_{b}+...$
 
 When no J molecules are present, $X_{j}= 0$ when only J molecules are present $X_{j} =1$
 It follows from the definition that, whatever the composition of the mixture, $X_{a}+ X_{b} +...=1$ and therefore that the sum of the partial pressure is equal to the total pressure:$$P_{a}+P_{b}+...=(X_{a}+X_{b}+...)P=P$$
 This relation is true for both real and perfect gases. If all the gases in the mixture were perfect they follow the **Dalton's Law:** 
 >[!tldr] The pressure exerted by a mixture of gases is the sum of pressures that each one would exert if it occupied the container alone.
 
 The above is only valid for perfect gases. so it is not used to define partial pressure. Partial pressure is defined by $P_{j}=X_{j}P$ which is valid for all gases.

## Checklist of concepts

1. The physical state of a sample of a substance, its physical condition, is defined by its physical properties. 
2. Mechanical equilibrium is the condition of equality of pressure on either side of a shared movable wall. 
3. An equation of state is an equation that interrelates the variables that define the state of a substance. 
4. Boyle’s and Charles’s laws are examples of a limiting law, a law that is strictly true only in a certain limit, in this case $p \rightarrow 0$. 
5. An isotherm is a line in a graph that corresponds to a single temperature. 
6. An isobar is a line in a graph that corresponds to a single pressure. 
7. An isochore is a line in a graph that corresponds to a single volume. 
8. A perfect gas is a gas that obeys the perfect gas law under all conditions. 
9. Dalton’s law states that the pressure exerted by a mixture of (perfect) gases is the sum of the pressures that each one would exert if it occupied the container alone.

## Checklist of Equations

| **Property**                        | **Equation**                                   | **Comment**                                        |     |
| ----------------------------------- | ---------------------------------------------- | -------------------------------------------------- | --- |
| Relation between temperature scales | $\frac{T}{K}=\frac{\theta}{^{\circ} C}+273.15$ | 273.15 is exact                                    |     |
| Perfect gas law                     | $pV = nRT$                                     | Valid for real gases in the limit $p\rightarrow 0$ |     |
| Partial pressure                    | $p_{j}=X_{j}p$                                 | Valid for all gases                                |     |
| Mole fraction                       | $X_{j}=n_{j}/n$ where  $n = n_{A}+n_{B}+...$   | Definition                                         |     |


# The Kinetic Model

The **kinetic theory of gasses or kinetic molecular theory (KMT)** assumes that the only contribution to the energy of the gas is from the kinetic energies of the molecules.

## The Model

The kinetic model is based on three assumptions:
1. The gas consists of molecules of mass $m$ in ceaseless random motion obeying the laws of classical mechanics.
2. The size of the molecules is negligible, in the sense that their diameters are much smaller than the average distance travelled between collisions; they are 'point-like'.
3. The molecules interact only through brief elastic collisions.

