# Root Locus

To draw the root locus:
1. It has **n** branches.
2. Each branch starts at an open loop pole and moves towards an open loop zero as **k** increases.
3. If number of zeros are greater than poles, then the branches goes to infinity, this condition is called asymptote.

>[!note] When all poles are to the left of the real axis makes it a stable system

Example:
$$H(s) = \frac{Y(s)}{U(s)}=\frac{s + 7}{s(s+5)(s+15)(s+20)}$$
## Example Code

```python
s = tf('s');

sys = (s +7)/(s*(s+5)*(s+15)*(s+20));

rlocus(sys)

axis([-22 3 -15 15])
```

## Results

![[rlocus_test(1).jpg]]

# Bode Plots

Bode plots are a fundamental tool in control system engineering, used to analyze the frequency
response of linear, time-invariant systems. They provide a graphical representation of a system's gain and phase shift across a range of frequencies. Bode plots are particularly useful for assessing system stability and performance in the frequency domain.

## Components of a Bode Plot

A Bode plot consists of two separate graphs:
1. **Magnitude Plot:** Shows how the system's gain (magnitude of the output/input ratio)
   varies with frequency, usually expressed in decibels (dB).
2. **Phase Plot:** Shows how the phase angle of the system's output relative to its input varies
   with frequency, usually expressed in degrees.

## Key Concepts

- **Gain Margin (GM):** The amount of gain increase required to bring the system to the verge of instability. It is measured at the phase crossover frequency, where the phase angle is -180 degrees.
- **Phase Margin (PM):** The additional phase lag required to bring the system to the verge of instability. It is measured at the gain crossover frequency, where the gain is 0 dB.

## Frequency Points

- **Gain Crossover Frequency (ωgc):** The frequency at which the gain is 0 dB. 
- **Phase-Crossover Frequency (ωpc):** The frequency at which the phase angle is -180 degrees.

## Stability Criteria

1. **Gain Margin:**
   - A positive gain margin indicates that the system can tolerate some increase in gain before becoming unstable. 
   - Gain margin $𝐺𝑀 = 20 log10\left( \frac{1}{|𝐺(𝑗𝜔𝑝𝑐) ∣ }\right)$,where $∣G(jωpc)|$ is the magnitude at phase crossover frequency.
 
2. **Phase Margin:** 
   - A positive phase margin indicates that the system can tolerate some additional phase lag before becoming unstable. 
   - Phase margin $PM=180∘+∠G(jωgc)$ where $∠G(jωgc)$ is the phase angle at gain crossover frequency

### Analyze Stability: 
- If both gain margin and phase margin are positive, the system is stable. 
- If either margin is zero or negative, the system is unstable.

## Example Code

```python
% Transfer Function = 50 / (s^3 + 9s^2 + 30s + 40)

clear all;
clc;

% Define numerator and denominator
n = [50];
d = [1 9 30 40];

% Create transfer function
sys = tf(n, d);

% Frequency range (decade spacing from 10^-1 to 10^2 with 4 points)
w = logspace(-1, 2, 4);

% Bode plot
bode(sys);
grid on;

% Extract magnitude and phase at given frequencies
[a, phase] = bode(sys, w);
mag = 20 * log10(a);
x = w';

% Gain margin, phase margin, and crossover frequencies
[G_Margin, P_Margin, w_phase_crossover, w_gain_crossover] = margin(sys);

% Convert gain margin to dB
Gain_Margin_dB = 20 * log10(G_Margin);

% Display table header
disp('Frequency(rad/sec)    Magnitude(dB)    Phase(degree)');

% Display frequency response values
for i = 1:4
    fprintf('%f\t\t %f\t\t %f \n', x(i), mag(i), phase(i));
end

```

### Results

Transfer function: $$\frac{50}{𝑠^3 + 9 𝑠^2 + 30 𝑠 + 40}$$

$GM = 4.6019$         $PM = 100.6674$

$W_{cg} = 5.4782$         $W_{cp} = 1.8483$

![[Bode_Plot.png|700|700|center]]

# Nyquist Plot

## Example Code

```python
% Define the numerator and denominator of the transfer function
num = [1];       % Numerator coefficients
den = [1 3 2];   % Denominator coefficients

% Create the transfer function
sys = tf(num, den);

% Plot the Nyquist diagram
figure;
nyquist(sys);
grid on;
title('Nyquist Plot of the System');

% Analyze the stability using the Nyquist plot
% The number of encirclements of the point (-1,0)
% combined with open-loop poles in the right-half plane
% determines the closed-loop stability.
```

![[Nyquist_plot 1.png]]