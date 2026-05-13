# CMOS Power Dissipation and Short-Channel Effects

This document answers the homework questions from the basics. It assumes you are new to CMOS power and short-channel MOSFET behavior.

## 1. Derivation of Switching or Dynamic Power Dissipation in CMOS

In a CMOS gate, most dynamic power is spent in charging and discharging capacitances. These capacitances include:

- Gate capacitances of the next-stage transistors.
- Diffusion capacitances of source/drain regions.
- Interconnect or wire capacitance.
- Parasitic capacitances inside the layout.

Consider a CMOS inverter driving a load capacitance $C_L$.

### Charging the Load Capacitance

When the input of the inverter goes from logic 1 to logic 0, the PMOS turns ON and charges the output load capacitance from $0$ to $V_{DD}$.

The energy stored in the capacitor after charging is:

$$
E_{\text{stored}} = \frac{1}{2} C_L V_{DD}^2
$$

However, the energy drawn from the power supply during this charging process is:

$$
E_{\text{supply}} = C_L V_{DD}^2
$$

The difference is dissipated as heat in the PMOS transistor:

$$
E_{\text{dissipated during charging}} = E_{\text{supply}} - E_{\text{stored}}
$$

$$
E_{\text{dissipated during charging}} = \frac{1}{2} C_L V_{DD}^2
$$

### Discharging the Load Capacitance

When the input goes from logic 0 to logic 1, the PMOS turns OFF and the NMOS turns ON. The load capacitance discharges from $V_{DD}$ to $0$.

The energy previously stored in the capacitor is:

$$
E_{\text{stored}} = \frac{1}{2} C_L V_{DD}^2
$$

This stored energy is dissipated as heat in the NMOS transistor.

### Energy for One Complete Output Cycle

One full output cycle means:

$$
0 \rightarrow 1 \rightarrow 0
$$

Energy dissipated in charging:

$$
\frac{1}{2} C_L V_{DD}^2
$$

Energy dissipated in discharging:

$$
\frac{1}{2} C_L V_{DD}^2
$$

Total energy dissipated per full switching cycle:

$$
E_{\text{cycle}} = C_L V_{DD}^2
$$

If the output switches $\alpha$ times per clock cycle, and the clock frequency is $f$, then the switching power is:

$$
P_{\text{switching}} = \alpha C_L V_{DD}^2 f
$$

This is the standard CMOS dynamic power equation.

### Physical Significance of Each Parameter

$P_{\text{switching}}$

: The average power dissipated due to charging and discharging capacitances.

$\alpha$

: Switching activity factor. It tells how often the node actually switches. A node that switches every clock cycle has high activity. A node that rarely changes has low activity.

$C_L$

: Load capacitance being charged and discharged. Larger capacitance requires more charge and more energy.

$V_{DD}$

: Supply voltage. Power depends on the square of supply voltage, so reducing $V_{DD}$ is very effective for reducing dynamic power.

$f$

: Clock frequency. Higher frequency means more switching events per second, so power increases linearly with frequency.

Important note: Some books define $\alpha$ differently. If $\alpha$ counts all output transitions, both rising and falling, the equation may appear with a factor of $\frac{1}{2}$. In many VLSI texts, $\alpha$ means the average number of $0 \rightarrow 1$ output transitions per clock cycle, giving:

$$
P_{\text{switching}} = \alpha C_L V_{DD}^2 f
$$

## 2. Effect of Technology Scaling on Leakage Power

In older CMOS technologies, dynamic power dominated and leakage power was very small. A transistor that was OFF behaved almost like an open switch.

In advanced technology nodes, leakage is no longer negligible because transistors are very small and several physical effects become stronger.

### Why Leakage Increases With Scaling

### Lower Threshold Voltage

To maintain speed when supply voltage is reduced, the threshold voltage $V_T$ is also reduced.

But subthreshold leakage current depends exponentially on threshold voltage:

$$
I_{\text{sub}} \propto e^{-V_T/(nV_{\text{thermal}})}
$$

So even a small reduction in threshold voltage can cause a large increase in leakage current.

### Thin Gate Oxide

As MOSFETs are scaled, the gate oxide becomes thinner. If the oxide is extremely thin, electrons can tunnel through it quantum mechanically.

This causes gate leakage current.

Modern technologies use high-k dielectrics and metal gates to reduce this problem, but leakage remains an important design issue.

### Short-Channel Effects

When the channel length becomes very small, the drain electric field affects the channel barrier near the source. This leads to effects such as:

- Drain Induced Barrier Lowering, or DIBL.
- Punch-through.
- Higher off-state current.

These effects make it harder for the gate to fully turn the transistor OFF.

### More Transistors on a Chip

Even if the leakage of one transistor is small, modern chips contain billions of transistors. The total leakage current can become very large.

### Higher Electric Fields

Scaling reduces dimensions faster than voltages. This increases electric fields inside the device, causing leakage and reliability issues such as hot-carrier effects.

### Why Leakage Is No Longer Negligible

Leakage used to be a small standby loss. In advanced nodes, it can become a major part of total power, especially in:

- Battery-powered devices.
- Always-on circuits.
- Memory arrays.
- Low-activity digital blocks.
- High-temperature environments.

Leakage is difficult because it exists even when the circuit is not switching.

## 3. Switching Power, Short-Circuit Power, Leakage Power, and Glitching Power

Total CMOS power can be written approximately as:

$$
P_{\text{total}} = P_{\text{switching}} + P_{\text{short-circuit}} + P_{\text{leakage}} + P_{\text{glitching}}
$$

## Switching Power

Switching power is the power consumed when capacitive nodes charge and discharge.

It is also called capacitive dynamic power.

Formula:

$$
P_{\text{switching}} = \alpha C_L V_{DD}^2 f
$$

It occurs only when signals change logic value. If no node switches, ideal switching power is zero.

Ways to reduce switching power:

- Reduce supply voltage.
- Reduce load capacitance.
- Reduce switching activity.
- Reduce frequency.
- Use clock gating.
- Optimize layout to reduce wire capacitance.

## Short-Circuit Power

Short-circuit power occurs during input transitions.

In a CMOS inverter:

- When input is low, PMOS is ON and NMOS is OFF.
- When input is high, PMOS is OFF and NMOS is ON.

But during the short time when the input is between logic 0 and logic 1, both PMOS and NMOS can be ON at the same time. This creates a temporary direct path:

$$
V_{DD} \rightarrow \text{PMOS} \rightarrow \text{NMOS} \rightarrow \text{GND}
$$

This current is called short-circuit current or crowbar current.

Short-circuit power increases when:

- Input transition is slow.
- Output load is small compared to drive strength.
- PMOS and NMOS are both strong.
- Supply voltage is high.

Ways to reduce short-circuit power:

- Use fast input transitions.
- Properly size transistors.
- Avoid excessive gate drive strength.
- Balance input and output slopes.

## Leakage Power

Leakage power is the power consumed even when the circuit is not switching.

Formula:

$$
P_{\text{leakage}} = I_{\text{leakage}} V_{DD}
$$

Leakage components include:

- Subthreshold leakage.
- Gate oxide tunneling leakage.
- Reverse-biased junction leakage.
- Gate-induced drain leakage.
- Band-to-band tunneling in advanced nodes.

Leakage is important in standby mode because the circuit may not be doing useful work but still consumes power.

Ways to reduce leakage:

- Use high-threshold-voltage transistors.
- Use power gating.
- Use multi-threshold CMOS.
- Use body biasing.
- Reduce temperature.
- Turn off unused blocks.

## Glitching Power

Glitching power is extra dynamic power caused by unnecessary transitions.

A glitch is a temporary unwanted pulse at a node before the correct final logic value is reached.

Example:

Suppose a logic gate has multiple inputs, and those inputs arrive at different times because of unequal path delays. The output may briefly switch to the wrong value and then switch back.

Even though the final output is correct, the temporary switching charges and discharges capacitances, wasting power.

Glitching power is part of dynamic power, but it is often discussed separately because it does not represent useful computation.

Ways to reduce glitching:

- Balance path delays.
- Use proper logic restructuring.
- Use pipelining carefully.
- Avoid unnecessary reconvergent paths.
- Register signals at suitable boundaries.

## 4. How Short-Channel Effects Increase Leakage and Reduce Performance

Short-channel effects occur when the MOSFET channel length becomes comparable to the source/drain depletion regions. In long-channel devices, the gate has strong control over the channel. In short-channel devices, the drain and source electric fields also strongly influence the channel.

This creates two major problems at the same time:

1. Leakage increases.
2. Performance becomes harder to improve.

### Leakage Increases

Short-channel effects reduce gate control. The transistor may not turn OFF properly.

Examples:

- DIBL lowers the source-channel barrier when drain voltage is high.
- Punch-through allows current to flow even when the gate is OFF.
- Lower threshold voltage increases subthreshold leakage.
- Thin oxides increase gate leakage.

Therefore, OFF current increases significantly.

### Performance Can Reduce or Saturate

Scaling is expected to make devices faster because shorter channels should reduce carrier travel time. However, short-channel effects introduce limitations:

- Velocity saturation limits carrier speed.
- Mobility degradation reduces current drive.
- Higher electric fields create reliability limits.
- Lower supply voltage reduces gate overdrive.
- Parasitic resistance and capacitance become more significant.

So the device may leak more but not become proportionally faster.

### Why This Makes Low-Power Design Difficult

For high performance, designers want:

- Low threshold voltage.
- High drive current.
- Short channel length.
- High switching speed.

For low leakage, designers want:

- High threshold voltage.
- Better gate control.
- Lower electric fields.
- Lower standby current.

These requirements conflict. A transistor designed for speed usually leaks more. A transistor designed for low leakage is usually slower. This tradeoff is one of the central challenges of low-power VLSI design.

## 5. Short-Channel Effects in MOSFETs

## Drain Induced Barrier Lowering, or DIBL

In a MOSFET, the gate controls the potential barrier between source and channel. In a long-channel MOSFET, the drain voltage has little effect on this barrier.

In a short-channel MOSFET, the drain is very close to the source. A high drain voltage can lower the potential barrier near the source. This makes it easier for carriers to enter the channel, even when the gate voltage is below threshold.

Result:

- Threshold voltage appears lower at high drain voltage.
- OFF current increases.
- Gate control becomes weaker.
- Static power increases.

## Punch-Through

Punch-through occurs when the depletion regions of the source and drain extend so much that they meet or nearly meet inside the channel.

When this happens, current can flow from source to drain even without proper gate control.

Result:

- Large leakage current.
- Loss of gate control.
- Poor transistor switching behavior.
- Possible device failure in severe cases.

## Surface Scattering

In a MOSFET, carriers move in a channel very close to the silicon-oxide interface. This interface is not perfectly smooth.

As the vertical electric field increases, carriers are pulled closer to the surface. They collide more often with imperfections and roughness at the interface.

This is called surface scattering.

Result:

- Carrier mobility decreases.
- Drain current decreases.
- Propagation delay increases.
- Device performance reduces.

## Hot Electron Effect

In short-channel MOSFETs, the electric field near the drain can be very high. Electrons traveling through this high field can gain large kinetic energy. These high-energy electrons are called hot electrons.

Some hot electrons can:

- Create additional electron-hole pairs by impact ionization.
- Enter the gate oxide.
- Get trapped in the oxide or at the interface.

Result:

- Threshold voltage shifts over time.
- Drain current reduces.
- Transistor characteristics degrade.
- Long-term reliability worsens.

## 6. Effect of Load Capacitance on Propagation Delay and Dynamic Power

Load capacitance directly affects both speed and power.

### Effect on Propagation Delay

Propagation delay is the time taken for the output to respond after the input changes.

For a CMOS gate, the output changes by charging or discharging the load capacitance. A larger capacitance takes more time to charge or discharge.

Approximate delay:

$$
t_p \approx R_{\text{on}} C_L
$$

where:

- $R_{\text{on}}$ is the effective ON resistance of the conducting transistor.
- $C_L$ is the load capacitance.

So if $C_L$ increases, delay increases. If $C_L$ decreases, delay decreases.

### Effect on Dynamic Power

Dynamic power is:

$$
P_{\text{dynamic}} = \alpha C_L V_{DD}^2 f
$$

So dynamic power is directly proportional to load capacitance.

If $C_L$ doubles, dynamic power doubles. If $C_L$ is reduced by 30%, dynamic power also reduces by 30%, assuming $\alpha$, $V_{DD}$, and $f$ remain constant.

### Simple Intuition

A larger capacitor is like a larger tank. It takes more charge to fill it and more time to fill it. Therefore:

- Larger $C_L$ means more delay.
- Larger $C_L$ means more dynamic power.

## 7. Percentage Reduction in Dynamic Power When Capacitance Is Reduced

Given:

$$
C_{\text{original}} = 80 \text{ fF}
$$

$$
\text{Capacitance reduction} = 30\%
$$

Dynamic power is:

$$
P_{\text{dynamic}} = \alpha C_L V_{DD}^2 f
$$

If $\alpha$, $V_{DD}$, and $f$ remain constant, dynamic power is directly proportional to capacitance:

$$
P_{\text{dynamic}} \propto C_L
$$

New capacitance:

$$
C_{\text{new}} = 80 \text{ fF} - 30\% \text{ of } 80 \text{ fF}
$$

$$
C_{\text{new}} = 80 \text{ fF} - 24 \text{ fF} = 56 \text{ fF}
$$

Percentage reduction in capacitance:

$$
30\%
$$

Therefore, percentage reduction in dynamic power:

$$
30\%
$$

Final answer:

$$
\text{Dynamic power reduces by } 30\%.
$$

## 8. Why Interconnect Capacitance Dominates Gate Capacitance in Deep-Submicron Technologies

In older technologies, transistor dimensions were large, and gate capacitance was a major part of total capacitance.

In deep-submicron technologies, transistor gates became very small, but wires did not scale as favorably.

### Reasons Interconnect Capacitance Dominates

### Wires Do Not Scale Like Transistors

Transistor dimensions shrink aggressively with each technology generation. However, global wires may remain long because chip size and system complexity do not shrink in the same way.

Long wires have significant capacitance.

### Smaller Spacing Between Wires

As metal lines become closer together, coupling capacitance between neighboring wires increases.

This means a wire has capacitance not only to the substrate but also to nearby wires.

### More Routing Layers and Higher Complexity

Modern chips contain huge numbers of signals and many metal layers. Routing density is high, so total wire capacitance becomes large.

### Gate Capacitance Per Device Reduces

As transistor gate length and width shrink, individual gate capacitances decrease.

### Repeated and Buffered Interconnects

Long wires often need repeaters or buffers. These add extra capacitance and power.

### Consequence

In deep-submicron VLSI, delay and power are often dominated by wires rather than transistor gates. This is why physical design, floorplanning, placement, and routing are very important for low-power and high-speed chips.

## 9. DIBL and Its Impact on Threshold Voltage

DIBL stands for Drain Induced Barrier Lowering.

In a MOSFET, current flows from source to drain when carriers can cross the source-channel potential barrier. Ideally, this barrier is controlled mainly by the gate voltage.

In a short-channel MOSFET, the drain is very close to the source. When drain voltage $V_{DS}$ is high, the drain electric field penetrates toward the source and lowers the source-channel barrier.

Because the barrier is lowered, a smaller gate voltage is needed to turn the transistor ON.

Therefore, the apparent threshold voltage decreases as drain voltage increases.

This can be written conceptually as:

$$
V_T \text{ decreases when } V_{DS} \text{ increases}
$$

or:

$$
\Delta V_T \approx -\eta \Delta V_{DS}
$$

where $\eta$ is the DIBL coefficient.

### Impact of DIBL

DIBL causes:

- Lower effective threshold voltage.
- Higher subthreshold leakage.
- Poor OFF-state behavior.
- Reduced noise margin.
- Increased standby power.
- More variation in circuit timing.

In digital CMOS, DIBL is harmful because the transistor may conduct current even when it is supposed to be OFF.

## 10. Punch-Through Effect in Short-Channel MOSFETs

Punch-through is a short-channel effect where the source and drain depletion regions merge or nearly merge.

### Normal Case

In a properly controlled MOSFET, the gate controls the channel. When the gate voltage is below threshold, there should be no strong conduction path from source to drain.

### Punch-Through Case

In a short-channel MOSFET:

- The source and drain are close together.
- Reverse-biased junctions create depletion regions around source and drain.
- When drain voltage increases, the drain depletion region expands toward the source.
- If the two depletion regions touch, the source and drain become electrically linked through the depleted region.

Then current can flow even when the gate voltage is low.

### Neat Explanation With Text Diagram

Long-channel device:

```text
Source     Channel controlled by gate      Drain
  |------------------- long ------------------|
  [depletion]                          [depletion]
```

Short-channel device with punch-through:

```text
Source       very short channel        Drain
  |--------------- short ----------------|
  [depletion] [regions merge] [depletion]
```

Once the depletion regions merge, the gate loses proper control over current flow.

### Effects of Punch-Through

- OFF-state leakage increases strongly.
- Threshold behavior becomes poor.
- Device cannot act as a good switch.
- Static power increases.
- Circuit reliability and noise margins degrade.

## 11. Surface Scattering and Its Effect on Carrier Mobility

Carrier mobility describes how easily carriers move through a semiconductor when an electric field is applied.

High mobility means carriers move easily, giving high current and faster switching.

In a MOSFET, the conducting channel forms near the silicon surface, just below the oxide. The interface between silicon and oxide is not perfectly smooth. It contains:

- Surface roughness.
- Interface traps.
- Crystal imperfections.
- Charge defects.

When the gate voltage is high, a strong vertical electric field pulls carriers closer to this interface.

The closer the carriers are to the surface, the more often they collide with roughness and defects. This scattering reduces their average velocity.

This phenomenon is called surface scattering.

### Effect on Mobility

Surface scattering reduces effective carrier mobility:

$$
\text{Mobility decreases as vertical electric field increases.}
$$

### Effect on MOSFET Performance

Reduced mobility causes:

- Lower drain current.
- Slower charging and discharging of capacitances.
- Larger propagation delay.
- Reduced transconductance.
- Lower circuit speed.

So surface scattering is one reason scaled MOSFETs do not become faster in exact proportion to their reduced channel length.

## 12. Velocity Saturation and Why It Limits Drain Current

In a MOSFET, carriers are accelerated by the lateral electric field from source to drain.

At low electric field, carrier velocity is approximately proportional to electric field:

$$
v = \mu E
$$

where:

- $v$ is carrier velocity.
- $\mu$ is mobility.
- $E$ is electric field.

This means increasing the drain voltage increases carrier velocity and drain current.

### What Happens at High Electric Field

In short-channel MOSFETs, the channel is very short. Even moderate drain voltage creates a very large electric field:

$$
E \approx \frac{V_{DS}}{L}
$$

When the electric field becomes very high, carriers cannot keep accelerating indefinitely. They collide frequently with the crystal lattice and lose energy as heat.

Eventually, carrier velocity reaches a maximum value called saturation velocity:

$$
v_{\text{sat}} \approx 10^7 \text{ cm/s}
$$

After this point, increasing electric field does not significantly increase carrier velocity.

### Why Drain Current Is Limited

Drain current depends on how much charge is in the channel and how fast carriers move:

$$
I_D \approx \text{charge density} \times \text{carrier velocity}
$$

If velocity saturates, the current cannot keep increasing strongly with drain voltage.

Thus, velocity saturation limits the drive current of scaled MOSFETs.

### Impact Ionization Consequences

- Current does not increase as much as long-channel theory predicts.
- Transistor speed improvement becomes limited.
- Propagation delay does not scale ideally.
- High electric fields cause reliability concerns.

## 13. Impact Ionization and Its Consequences

Impact ionization occurs when a high-energy carrier collides with the silicon lattice and creates additional electron-hole pairs.

### Hot Electron Mechanism

In a short-channel MOSFET, the electric field near the drain can be very high.

An electron moving toward the drain can gain high kinetic energy. If it collides with a silicon atom strongly enough, it can break a covalent bond and generate:

- One extra electron.
- One hole.

This creates additional carriers.

### In an NMOS Device

Electrons are the main channel carriers. Near the drain, hot electrons can generate electron-hole pairs.

- The generated electrons are collected by the drain.
- The generated holes may flow into the substrate or body.

This creates substrate current.

### Consequences

Impact ionization can cause:

- Increased drain current.
- Substrate current.
- Body potential changes.
- Parasitic bipolar transistor action.
- Hot-carrier degradation.
- Threshold voltage shifts.
- Reliability problems over time.

In severe cases, impact ionization can contribute to latch-up or breakdown-related failures.

## 14. Hot Electron Effect and Long-Term Reliability

The hot electron effect is caused by electrons gaining high energy in the strong electric field near the drain of a short-channel MOSFET.

These electrons are called hot because they have much more kinetic energy than carriers in thermal equilibrium. They are not necessarily physically hot in temperature; hot means energetically hot.

### Mechanism

1. A high drain voltage creates a strong electric field near the drain.
2. Electrons traveling through the channel gain high energy.
3. Some electrons cause impact ionization.
4. Some electrons enter or damage the gate oxide.
5. Some become trapped in the oxide or at the silicon-oxide interface.

### Effects on the Device

Trapped charge changes the electric field inside the MOSFET. This causes:

- Threshold voltage shift.
- Reduced drain current.
- Reduced transconductance.
- Slower circuit operation.
- Increased noise.
- Degradation of oxide quality.

### Long-Term Reliability Problem

Hot electron damage accumulates over time. A chip may work correctly when new, but after many hours or years of operation, transistor parameters can drift.

This can cause:

- Timing failures.
- Increased delay.
- Logic errors.
- Reduced lifetime.
- Permanent degradation.

Designers reduce hot electron effects by:

- Lowering supply voltage.
- Using lightly doped drain structures.
- Reducing peak electric fields.
- Careful device engineering.
- Avoiding excessive voltage stress.

## 15. Numerical Problem: Switching Power of a CMOS Gate

Given:

$$
C_L = 50 \text{ fF}
$$

$$
V_{DD} = 1.2 \text{ V}
$$

$$
f = 200 \text{ MHz}
$$

$$
\alpha = 0.6
$$

Formula:

$$
P_{\text{switching}} = \alpha C_L V_{DD}^2 f
$$

Convert units:

$$
50 \text{ fF} = 50 \times 10^{-15} \text{ F}
$$

$$
200 \text{ MHz} = 200 \times 10^6 \text{ Hz}
$$

Substitute:

$$
P_{\text{switching}} = 0.6 \times 50 \times 10^{-15} \times (1.2)^2 \times 200 \times 10^6
$$

Calculate voltage square:

$$
(1.2)^2 = 1.44
$$

So:

$$
P_{\text{switching}} = 0.6 \times 50 \times 10^{-15} \times 1.44 \times 200 \times 10^6
$$

First calculate:

$$
50 \times 200 = 10000
$$

$$
10^{-15} \times 10^6 = 10^{-9}
$$

So:

$$
P_{\text{switching}} = 0.6 \times 1.44 \times 10000 \times 10^{-9}
$$

$$
0.6 \times 1.44 = 0.864
$$

$$
P_{\text{switching}} = 0.864 \times 10000 \times 10^{-9}
$$

$$
P_{\text{switching}} = 8640 \times 10^{-9} \text{ W}
$$

$$
P_{\text{switching}} = 8.64 \times 10^{-6} \text{ W}
$$

Final answer:

$$
P_{\text{switching}} = 8.64\,\mu\text{W}
$$

## Quick Revision Summary

### Important Power Formulas

Switching power:

$$
P_{\text{switching}} = \alpha C_L V_{DD}^2 f
$$

Leakage power:

$$
P_{\text{leakage}} = I_{\text{leakage}} V_{DD}
$$

Delay approximation:

$$
t_p \approx R_{\text{on}} C_L
$$

### Key Takeaways

- Dynamic power mainly comes from charging and discharging capacitances.
- Supply voltage is very important because dynamic power depends on $V_{DD}^2$.
- Load capacitance increases both delay and dynamic power.
- Leakage power exists even when the circuit is idle.
- Short-channel effects make leakage worse by weakening gate control.
- Interconnect capacitance dominates in modern technologies because wires do not scale as well as transistors.
- DIBL lowers threshold voltage at high drain voltage.
- Punch-through creates unwanted source-to-drain conduction.
- Surface scattering lowers carrier mobility.
- Velocity saturation limits current in short-channel devices.
- Impact ionization and hot electrons create reliability problems.
