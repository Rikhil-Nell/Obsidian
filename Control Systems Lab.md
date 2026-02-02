# Experiment 1

**Aim:** To plot the unit step response of a given transfer function and determine its time-domain performance specifications, such as delay time, rise time, peak time, and peak overshoot

**Requirements:** MATLAB/Simulink, Control Systems Toolbox

**Code:**

```python
clc;
clear all;
close all;
% --- 1. System Definitions ---
k_dc = 1;
w_n = 10;
s = tf('s');

% Define all four transfer functions
G_un = k_dc * w_n^2 / (s^2 + 2 * 0 * w_n * s + w_n^2);
G_under = k_dc * w_n^2 / (s^2 + 2 * 0.2 * w_n * s + w_n^2);
G_critical = k_dc * w_n^2 / (s^2 + 2 * 1 * w_n * s + w_n^2);
G_over = k_dc * w_n^2 / (s^2 + 2 * 1.2 * w_n * s + w_n^2);

% --- 2. Step Response Plots ---
% Plot the three damped systems together, as they are comparable
figure;
step(G_under, G_critical, G_over);
title('Step Response for Damped Systems');
legend('Under-damped (Zeta=0.2)', 'Critically-damped (Zeta=1.0)', 'Over-damped (Zeta=1.2)');
grid on;

% Plot the undamped system separately, as its behavior is different
figure;
step(G_un);
title('Step Response for Undamped System (Zeta=0)');
legend('Un-damped');
grid on;

% --- 3. Pole-Zero Map ---

% Plot all pole-zero maps on one figure using the direct method
figure;
pzmap(G_un, G_under, G_critical, G_over);
title('Pole-Zero Map Comparison');
legend('Un-damped (Poles on jw-axis)', ...
'Under-damped (Complex Conjugate)', ...
'Critically-damped (Repeated Real Pole)', ...
'Over-damped (Distinct Real Poles)');
grid on;

% --- 4. Step Info ---
% Display the info for the underdamped case as requested
disp('Stepinfo for Underdamped (Zeta=0.2) System:');
disp(stepinfo(G_under));
```

**Result:** The unit step response for a second-order system was plotted, and its key time-domain performance indices (rise time, peak overshoot, etc.) were calculated and analyzed.


# Experiment 2

**Aim:** To determine the steady-state errors of a given transfer function for a unit step input.

**Requirements:** MATLAB/Simulink, Control Systems Toolbox

**Code:**
```python
clc;
clear all;
close all;

% Define the closed-loop transfer function
h = tf([4], [1 3 4]);

% Calculate steady-state error using dcgain
% dcgain() finds the exact final value for a unit step input
final_value = dcgain(h);
sserror = abs(1 - final_value)

% Plot the step response
figure;
step(h);
title('Step Response');
grid on;
```

**Result:** The steady-state error for a given transfer function was determined by calculating the system's DC gain and comparing it to the unit step input.

# Experiment 3

**Aim:** To analyze the stability and frequency response of a system by generating and interpreting its Root Locus, Bode plot, and Nyquist plot using MATLAB.

**Requirements:** MATLAB/Simulink, Control Systems Toolbox

**Code:**
```python
clc;
clear all;
close all;

% --- Part 1: Root Locus ---
s = tf('s');
sys_rl = (s + 7) / (s * (s + 5) * (s + 15) * (s + 20));
figure;
rlocus(sys_rl);
title('Root Locus');
grid on;

% --- Part 2: Bode Plot ---
sys_bode = tf([50], [1 9 30 40]);
figure;
margin(sys_bode); % This command plots the Bode and shows margins
grid on;

% --- Part 3: Nyquist Plot ---
sys_nyquist = tf([1], [1 3 2]);
figure;
nyquist(sys_nyquist);
title('Nyquist Plot');
grid on;
```

**Result:** The stability of different systems was analyzed graphically. The Root Locus showed pole movement, the Bode plot provided gain and phase margins, and the Nyquist plot showed stability based on encirclements of the -1 point.


# Experiment 4

**Aim:** To perform stability analysis on a first-order discrete system and a second-order continuous system by examining their pole locations.

**Requirements:** MATLAB/Simulink, Control Systems Toolbox

**Code:**
- First-Order Discrete System
```python
clc; clear all; close all;

sys_d = feedback(10 * tf([0.05], [1 -0.95], 1), 1);

poles = pole(sys_d)

if all(abs(poles) < 1)
    disp('Discrete system is stable.');
else
    disp('Discrete system is unstable.');
end

figure; step(sys_d); title('Discrete System Step Response');
```

- Second-Order Continuous System
```python
clc; clear all; close all;

sys_c = tf([420 0], [1 620 4000]);

poles = pole(sys_c)

if all(real(poles) < 0)
    disp('Continuous system is stable.');
else
    disp('Continuous system is unstable.');
end

figure; step(sys_c); title('Continuous System Step Response');
```

**Result:** The stability of first-order discrete and second-order continuous systems was determined by calculating their poles. Stability was verified by checking if the poles were inside the unit circle (for discrete) or in the left-half plane (for continuous).

# Experiment 5

**Aim:** To determine the stability of a system using the Routh-Hurwitz method.

**Requirements:** MATLAB/Simulink, Control Systems Toolbox

**Code:**

```python
clear
clc
e = input('Enter the coefficients of characteristic equation: ');
disp('-----------------------------------');
l = length(e);
m = mod(l, 2);

% Populate first two rows (a and b) from coefficients (e)
if m == 0
    a = e(1:2:end);
    b = e(2:2:end);
else
    a = e(1:2:end);
    b = [e(2:2:end), 0];
end

% Generate the Routh matrix (c)
l1 = length(a);
c = zeros(l, l1);
c(1, :) = a;
c(2, :) = b;

for m = 3:l
    for n = 1:l1-1
        % Handle potential division by zero (row of zeros)
        if c(m-1, 1) == 0
            c(m-1, 1) = 1e-6; % Use epsilon
        end
        c(m, n) = -(1/c(m-1, 1)) * det([c((m-2), 1) c((m-2), (n+1)); c((m-1), 1) c((m-1), (n+1))]);
    end
end

disp('The Routh matrix:')
disp(c)

% Check stability by counting sign changes in the first column
first_col = c(:, 1);
sign_changes = 0;
for k = 1:l-1
    if sign(first_col(k)) ~= sign(first_col(k+1)) && first_col(k+1) ~= 0
        sign_changes = sign_changes + 1;
    end
end

if sign_changes > 0
    disp('System is Unstable');
else
    disp('System is Stable');
end
```

**Result:** A MATLAB program was created to automatically generate the Routh-Hurwitz array from a system's characteristic equation. The stability was determined by checking for sign changes in the first column of the array, which indicate poles in the right-half plane.

# Experiment 6

**Aim:** To analyze the step response of a second-order system with P, PI, PD, and PID controller configurations to observe their effects on performance.

**Requirements:** MATLAB/Simulink, Control Systems Toolbox

**Code:**
- Open Loop
```python
clc; clear all; close all;
plant = tf([1], [1 10 20]);
figure; step(plant); title('Open-Loop Step Response');
```

- P control
```python
clc; clear all; close all;
plant = tf([1], [1 10 20]);
contr_P = 300;
sys_cl_P = feedback(contr_P * plant, 1);
figure; step(sys_cl_P, 0:0.01:2); title('Closed-Loop: P Control');
```

- PI Control
```python
clc; clear all; close all;
plant = tf([1], [1 10 20]);
contr_PI = tf([30 70], [1 0]); % Kp=30, Ki=70
sys_cl_PI = feedback(contr_PI * plant, 1);
figure; step(sys_cl_PI, 0:0.01:2); title('Closed-Loop: PI Control');
```

- PD Control
```python
clc; clear all; close all;
plant = tf([1], [1 10 20]);
contr_PD = tf([10 300], 1); % Kd=10, Kp=300
sys_cl_PD = feedback(contr_PD * plant, 1);
figure; step(sys_cl_PD, 0:0.01:2); title('Closed-Loop: PD Control');
```

- PID Control
```python
clc; clear all; close all;
plant = tf([1], [1 10 20]);
contr_PID = tf([50 350 300], [1 0]); % Kd=50, Kp=350, Ki=300
sys_cl_PID = feedback(contr_PID * plant, 1);
figure; step(sys_cl_PID, 0:0.01:2); title('Closed-Loop: PID Control');
```

**Result:** The effects of P, PI, PD, and PID controllers were demonstrated. The PID controller, when properly tuned, combined the benefits of all three terms (fast response, zero steady-state error, and low overshoot) to achieve the desired performance.