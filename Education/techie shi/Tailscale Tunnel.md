# 🧠 What You’re Building

An exit node is:

> A Tailscale machine that advertises itself as a full internet gateway (0.0.0.0/0 and optionally ::/0).

Client device routing becomes:

```
default route → tailscale0 → VPS → public internet
```

---

# ✅ Prerequisites (Any VPS)

1. Ubuntu 22.04+ (recommended)
    
2. Public IPv4 address
    
3. Security group / firewall allows:
    
    - TCP 22 (SSH, restrict to your IP)
        
    - UDP 41641 (recommended for direct WireGuard)
        
    - Outbound: allow all
        

You do NOT open random ports for games.

---

# 🔧 Step 1 — Update System

```
sudo apt update
sudo apt upgrade -y
```

---

# 🔧 Step 2 — Install Tailscale

Official method:

```
curl -fsSL https://tailscale.com/install.sh | sh
```

Verify:

```
tailscale version
```

---

# 🔧 Step 3 — Enable IP Forwarding (Critical)

Exit nodes must forward traffic.

### Enable IPv4 forwarding:

Temporary:

```
sudo sysctl -w net.ipv4.ip_forward=1
```

Permanent:

Edit:

```
sudo nano /etc/sysctl.conf
```

Add:

```
net.ipv4.ip_forward=1
```

Apply:

```
sudo sysctl -p
```

Verify:

```
cat /proc/sys/net/ipv4/ip_forward
```

Must print `1`.

---

# 🔧 Step 4 — Enable IPv6 Forwarding (Only If VPS Has Public IPv6)

First check:

```
ip a
```

If you see a **global IPv6** (not just `fe80::`), then:

Enable:

```
sudo sysctl -w net.ipv6.conf.all.forwarding=1
```

Make permanent in `/etc/sysctl.conf`:

```
net.ipv6.conf.all.forwarding=1
```

Apply:

```
sudo sysctl -p
```

If your VPS does NOT have public IPv6 → skip IPv6 entirely.

---

# 🔧 Step 5 — Bring Tailscale Up as Exit Node

### IPv4-only setup (most common):

```
sudo tailscale up --advertise-exit-node --advertise-routes=0.0.0.0/0
```

### Dual-stack (if VPS supports IPv6):

```
sudo tailscale up --advertise-exit-node --advertise-routes=0.0.0.0/0,::/0
```

Authenticate in browser.

---

# 🔧 Step 6 — Approve Exit Node in Admin Console

Go to:

[https://login.tailscale.com/admin/machines](https://login.tailscale.com/admin/machines)

For your VPS:

- Enable “Use as exit node”
    
- Approve advertised routes
    

Without this, clients cannot use it.

---

# 🔧 Step 7 — Confirm NAT Is Active

Tailscale usually auto-adds MASQUERADE.

Check:

```
sudo iptables -t nat -L -n
```

You should see MASQUERADE for outbound interface (e.g., eth0/ens5).

If missing:

```
sudo iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE
```

Persist:

```
sudo apt install iptables-persistent -y
sudo netfilter-persistent save
```

---

# 🔧 Step 8 — Optional Performance Tuning (UDP GRO)

Find interface name:

```
ip a
```

Then:

```
sudo ethtool -K ens5 rx-udp-gro-forwarding on
sudo ethtool -K ens5 rx-gro-list off
```

Replace `ens5` with your interface.

This improves high-throughput UDP forwarding.

For gaming loads, not critical — but good hygiene.

---

# 🔧 Step 9 — Enable Exit Node on Client

On your laptop:

GUI:

- Select VPS
    
- Toggle “Use exit node”
    

CLI:

```
tailscale up --exit-node=<hostname>
```

---

# 🔧 Step 10 — Verify It Works

On client:

Check public IP:

```
curl ifconfig.me
```

Must show VPS public IP.

Check route:

```
ip route
```

Default route should point to `tailscale0`.

---

# 🔐 Security Hardening (Always Do This)

Disable password SSH:

Edit:

```
sudo nano /etc/ssh/sshd_config
```

Ensure:

```
PasswordAuthentication no
```

Restart:

```
sudo systemctl restart ssh
```

Enable firewall:

```
sudo ufw allow OpenSSH
sudo ufw enable
```

Do NOT block outbound traffic.

---

# 📡 How Traffic Flows Internally

When exit node is enabled:

Client routing table changes:

```
0.0.0.0/0 → tailscale0
```

VPS receives packet:

- Decrypt
    
- NAT
    
- Forward via eth0
    
- Return path symmetric
    

University only sees:  
Encrypted stream to VPS.

---

# ⚠️ Common Mistakes

1. Forgetting IP forwarding
    
2. Not approving routes in admin console
    
3. Wrong VPS region (adds latency)
    
4. Advertising IPv6 when VPS doesn’t support it
    
5. Blocking outbound traffic in firewall
    

---

# 📈 Performance Reality

Exit node:

- Does NOT increase base bandwidth
    
- Adds small latency equal to hop to VPS
    
- Bypasses filtering/firewalls
    
- Hides P2P signatures
    

Choose VPS region near you.

---

# 🧠 Final Clean Mental Model

Exit node =

> A private encrypted default gateway you control.

It is not magic speed.  
It is controlled routing.

---

If you want next-level understanding, we can go deeper into:

- Policy routing tables
    
- How Tailscale injects routes
    
- How NAT interacts with conntrack
    
- How DERP fallback works when UDP is blocked
    

Your call.