# AM Modulation

```python
clc; clear; close all;

% --- BASIC SIGNALS ---
fs = 1e4;                     % Sampling freq
t = 0:1/fs:0.01;              % Time axis (10 ms)
fm = 100; fc = 1000;          % Message & carrier freqs
Am = 1; Ac = 1;               % Amplitudes

m = Am*cos(2*pi*fm*t);        % Message signal
c = Ac*cos(2*pi*fc*t);        % Carrier signal
ka = 0.5;                     % Modulation index (0.5 for clarity)

% --- AM MODULATION ---
s = (1 + ka*m).*c;             % AM modulated signal

% --- DEMODULATION (Envelope detection via rectification + LPF) ---
[env,~] = envelope(s);            % Demodulated message

% --- FREQUENCY AXIS ---
N = length(t);
f = (-N/2:N/2-1)*(fs/N);

% --- FFTs ---
M = fftshift(abs(fft(m,N))/N);
C = fftshift(abs(fft(c,N))/N);
S = fftshift(abs(fft(s,N))/N);
R = fftshift(abs(fft(env,N))/N);

% --- TIME DOMAIN PLOTS ---
figure('Name','AM Modulation','NumberTitle','off');
subplot(4,1,1); plot(t,m); title('Message Signal'); ylabel('m(t)');
subplot(4,1,2); plot(t,c); title('Carrier Signal'); ylabel('c(t)');
subplot(4,1,3); plot(t,s); title('AM Modulated Signal'); ylabel('s(t)');
subplot(4,1,4); plot(t,env); title('Demodulated Signal'); ylabel('m_{rec}(t)');
xlabel('Time (s)');

% --- FREQUENCY DOMAIN PLOTS ---
figure('Name','Frequency Spectra','NumberTitle','off');
subplot(4,1,1); plot(f,M); title('Message Spectrum'); ylabel('|M(f)|');
subplot(4,1,2); plot(f,C); title('Carrier Spectrum'); ylabel('|C(f)|');
subplot(4,1,3); plot(f,S); title('Modulated Spectrum'); ylabel('|S(f)|');
subplot(4,1,4); plot(f,R); title('Demodulated Spectrum'); ylabel('|R(f)|');
xlabel('Frequency (Hz)');
```

# FM Modulation

```python
clc; clear; close all;

% --- BASIC SETUP ---
fs = 1e5;                        % Sampling freq (must be high for FM)
t = 0:1/fs:0.01;                 % Time vector (10 ms)
fc = 10000; fm = 200;            % Carrier & message freqs (Hz)
Am = 1; Ac = 1;                  % Amplitudes
kf = 2*pi*50;                    % Frequency sensitivity (Hz/V)
beta = kf*Am/fm;                 % Modulation index

% --- SIGNALS ---
m = Am*cos(2*pi*fm*t);           % Message
s = Ac*cos(2*pi*fc*t + beta*sin(2*pi*fm*t));   % FM signal

% --- DEMODULATION ---
m_rec = fmdemod(s, fc, fs, kf);  % Recover message using MATLAB built-in

% --- FREQUENCY AXIS ---
N = length(t); f = (-N/2:N/2-1)*(fs/N);
M = fftshift(abs(fft(m))/N);
S = fftshift(abs(fft(s))/N);
R = fftshift(abs(fft(m_rec))/N);

% --- TIME DOMAIN PLOTS ---
figure;
subplot(3,1,1); plot(t,m); title('Message Signal'); ylabel('m(t)');
subplot(3,1,2); plot(t,s); title('FM Modulated Signal'); ylabel('s(t)');
subplot(3,1,3); plot(t,m_rec); title('Demodulated Signal'); ylabel('m_{rec}(t)');
xlabel('Time (s)');

% --- FREQUENCY DOMAIN PLOTS ---
figure;
subplot(3,1,1); plot(f,M); title('Message Spectrum'); ylabel('|M(f)|');
subplot(3,1,2); plot(f,S); title('FM Spectrum'); ylabel('|S(f)|');
subplot(3,1,3); plot(f,R); title('Demodulated Spectrum'); ylabel('|R(f)|');
xlabel('Frequency (Hz)');
```

# PAM Modulation

```python
clc; clear; close all;

% --- PARAMETERS ---
fs = 1e5;                     % Sampling frequency
t = 0:1/fs:0.01;              % Time vector (10 ms)
fm = 200;                     % Message frequency
fc = 2000;                    % Carrier (sampling pulse) frequency
Am = 1;                       % Message amplitude

% --- SIGNALS ---
m = Am*sin(2*pi*fm*t);        % Message signal
c = square(2*pi*fc*t, 50);    % Carrier pulse (50% duty)
s = m .* (c > 0);             % PAM signal (sample where carrier is high)

% --- DEMODULATION ---
d = s .* (c > 0);             % Re-multiply by same carrier (sync detection)
[b,a] = butter(6, fm*2/fs);   % Low-pass filter (cutoff ≈ 2× message freq)
m_rec = filtfilt(b,a,d);      % Reconstructed message

% --- FREQUENCY AXIS ---
N = length(t); f = (-N/2:N/2-1)*(fs/N);
M = fftshift(abs(fft(m))/N);
C = fftshift(abs(fft(c))/N);
S = fftshift(abs(fft(s))/N);
R = fftshift(abs(fft(m_rec))/N);

% --- TIME DOMAIN PLOTS ---
figure('Name','PAM Modulation (Time Domain)','NumberTitle','off');
subplot(4,1,1); plot(t,m); title('Message Signal'); ylabel('m(t)');
subplot(4,1,2); plot(t,c); title('Carrier (Pulse Train)'); ylabel('c(t)');
subplot(4,1,3); plot(t,s); title('PAM Modulated Signal'); ylabel('s(t)');
subplot(4,1,4); plot(t,m_rec); title('Demodulated Signal'); ylabel('m_{rec}(t)');
xlabel('Time (s)');

% --- FREQUENCY DOMAIN PLOTS ---
figure('Name','PAM Modulation (Frequency Domain)','NumberTitle','off');
subplot(4,1,1); plot(f,M); title('Message Spectrum'); ylabel('|M(f)|');
subplot(4,1,2); plot(f,C); title('Carrier Spectrum'); ylabel('|C(f)|');
subplot(4,1,3); plot(f,S); title('PAM Spectrum'); ylabel('|S(f)|');
subplot(4,1,4); plot(f,R); title('Demodulated Spectrum'); ylabel('|R(f)|');
xlabel('Frequency (Hz)');
```

# PWM Modulation

```python
clc; clear; close all;

% --- PARAMETERS ---
fs = 1e5;                      % Sampling frequency
t = 0:1/fs:0.02;               % Time vector (20 ms for clarity)
fm = 200;                      % Message frequency
fc = 2000;                     % Carrier (sawtooth) frequency
Am = 1;                        % Message amplitude

% --- SIGNALS ---
m = Am*sin(2*pi*fm*t);         % Message signal
c = sawtooth(2*pi*fc*t, 1);    % Sawtooth carrier (-1 to +1)
s = (m >= c);                  % PWM signal (width ∝ message amplitude)

% --- DEMODULATION ---
[b,a] = butter(6, fm*2/fs);    % Low-pass filter around message freq
m_rec = filtfilt(b,a,double(s)); % Demodulated (reconstructed) message

% --- FREQUENCY AXIS ---
N = length(t); f = (-N/2:N/2-1)*(fs/N);
M = fftshift(abs(fft(m))/N);
C = fftshift(abs(fft(c))/N);
S = fftshift(abs(fft(s))/N);
R = fftshift(abs(fft(m_rec))/N);

% --- TIME DOMAIN PLOTS ---
figure('Name','PWM Modulation (Time Domain)','NumberTitle','off');
subplot(4,1,1); plot(t,m); title('Message Signal'); ylabel('m(t)');
subplot(4,1,2); plot(t,c); title('Carrier (Sawtooth)'); ylabel('c(t)');
subplot(4,1,3); plot(t,s); title('PWM Modulated Signal'); ylabel('s(t)');
subplot(4,1,4); plot(t,m_rec); title('Demodulated Signal'); ylabel('m_{rec}(t)');
xlabel('Time (s)');

% --- FREQUENCY DOMAIN PLOTS ---
figure('Name','PWM Modulation (Frequency Domain)','NumberTitle','off');
subplot(4,1,1); plot(f,M); title('Message Spectrum'); ylabel('|M(f)|');
subplot(4,1,2); plot(f,C); title('Carrier Spectrum'); ylabel('|C(f)|');
subplot(4,1,3); plot(f,S); title('PWM Spectrum'); ylabel('|S(f)|');
subplot(4,1,4); plot(f,R); title('Demodulated Spectrum'); ylabel('|R(f)|');
xlabel('Frequency (Hz)');
```

# ASK Modulation

```python
clc; clear; close all;

% --- PARAMETERS ---
fs = 1e5;                       % Sampling frequency
t = 0:1/fs:0.01;                % Time vector (10 ms)
fb = 200;                       % Bit/message frequency
fc = 2000;                      % Carrier frequency
Am = 1; Ac = 1;                 % Amplitudes

% --- SIGNALS ---
m = (square(2*pi*fb*t) + 1)/2;  % Message (0 or 1 levels)
c = Ac*cos(2*pi*fc*t);          % Carrier signal
s = m .* c;                     % ASK modulated signal

% --- DEMODULATION ---
d = s .* c;                     % Coherent detection
[b,a] = butter(6, fb*2/fs);     % Low-pass filter near bit rate
m_rec = filtfilt(b,a,d);        % Demodulated baseband
m_rec = m_rec / max(m_rec);     % Normalize (for clean shape)

% --- FREQUENCY AXIS ---
N = length(t); f = (-N/2:N/2-1)*(fs/N);
M = fftshift(abs(fft(m))/N);
C = fftshift(abs(fft(c))/N);
S = fftshift(abs(fft(s))/N);
R = fftshift(abs(fft(m_rec))/N);

% --- TIME DOMAIN PLOTS ---
figure('Name','ASK Modulation (Time Domain)','NumberTitle','off');
subplot(4,1,1); plot(t,m); title('Message Signal (Binary)'); ylabel('m(t)');
subplot(4,1,2); plot(t,c); title('Carrier Signal'); ylabel('c(t)');
subplot(4,1,3); plot(t,s); title('ASK Modulated Signal'); ylabel('s(t)');
subplot(4,1,4); plot(t,m_rec); title('Demodulated Signal'); ylabel('m_{rec}(t)');
xlabel('Time (s)');

% --- FREQUENCY DOMAIN PLOTS ---
figure('Name','ASK Modulation (Frequency Domain)','NumberTitle','off');
subplot(4,1,1); plot(f,M); title('Message Spectrum'); ylabel('|M(f)|');
subplot(4,1,2); plot(f,C); title('Carrier Spectrum'); ylabel('|C(f)|');
subplot(4,1,3); plot(f,S); title('ASK Spectrum'); ylabel('|S(f)|');
subplot(4,1,4); plot(f,R); title('Demodulated Spectrum'); ylabel('|R(f)|');
xlabel('Frequency (Hz)');
```

# BPSK

```python
clc; clear; close all;

% --- PARAMETERS ---
fs = 1e5;                       % Sampling frequency
t = 0:1/fs:0.01;                % Time vector (10 ms)
fb = 200;                       % Bit (message) frequency
fc = 2000;                      % Carrier frequency
Ac = 1;                         % Carrier amplitude

% --- SIGNALS ---
m = square(2*pi*fb*t);          % Message: ±1 (binary data)
c = Ac*cos(2*pi*fc*t);          % Carrier
s = m .* c;                     % BPSK signal (phase shift via ±1)

% --- DEMODULATION ---
d = s .* c;                     % Coherent detection
[b,a] = butter(6, fb*2/fs);     % Low-pass filter
m_rec = filtfilt(b,a,d);        % Baseband recovered message
m_rec = sign(m_rec);            % Decision device (restore ±1)

% --- FREQUENCY AXIS ---
N = length(t); f = (-N/2:N/2-1)*(fs/N);
M = fftshift(abs(fft(m))/N);
C = fftshift(abs(fft(c))/N);
S = fftshift(abs(fft(s))/N);
R = fftshift(abs(fft(m_rec))/N);

% --- TIME DOMAIN PLOTS ---
figure('Name','BPSK Modulation (Time Domain)','NumberTitle','off');
subplot(4,1,1); plot(t,m); title('Message Signal (±1)'); ylabel('m(t)');
subplot(4,1,2); plot(t,c); title('Carrier Signal'); ylabel('c(t)');
subplot(4,1,3); plot(t,s); title('BPSK Modulated Signal'); ylabel('s(t)');
subplot(4,1,4); plot(t,m_rec); title('Demodulated Signal'); ylabel('m_{rec}(t)');
xlabel('Time (s)');

% --- FREQUENCY DOMAIN PLOTS ---
figure('Name','BPSK Modulation (Frequency Domain)','NumberTitle','off');
subplot(4,1,1); plot(f,M); title('Message Spectrum'); ylabel('|M(f)|');
subplot(4,1,2); plot(f,C); title('Carrier Spectrum'); ylabel('|C(f)|');
subplot(4,1,3); plot(f,S); title('BPSK Spectrum'); ylabel('|S(f)|');
subplot(4,1,4); plot(f,R); title('Demodulated Spectrum'); ylabel('|R(f)|');
xlabel('Frequency (Hz)');
```