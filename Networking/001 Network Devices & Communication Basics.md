---
tags:
  - "#Networking"
---
## **Hosts: The Foundation of Communication**

A **Host** is any device capable of sending or receiving traffic. Common examples include:

- **Computers**
- **Laptops**
- **Smartphones**
- **Cloud Servers**
- **IoT Devices**

### **Types of Hosts:**

1. **Clients**: Initiate requests (e.g., browsing a website).
2. **Servers**: Respond to requests (e.g., delivering a webpage).

> _Note_: Any device can act as a server with the appropriate software installed.

---

## **IP Address: The Identity of Hosts**

An **IP Address** uniquely identifies every host in a network. Data packets exchanged between hosts are stamped with:

- **Source IP Address**: Origin of the data.
- **Destination IP Address**: Target recipient.

### **Structure of an IP Address:**

- **Size**: 32 bits (IPv4).
- **Format**: Divided into 4 octets, each represented in decimal form (e.g., `192.168.1.1`).
- ![[Pasted image 20241217224922.png]]

#### **Visualizing an IP Address:**

![[IP address.excalidraw|center]]  

### **Subnetting: Organizing IPs**

IP addresses are assigned hierarchically via **Subnetting**, which divides a larger network into smaller, more manageable sub-networks.

---

## **What is a Network?**

A **Network** enables communication between hosts, either wired or wireless. It can be thought of as:

- A **logical group of hosts** needing similar connectivity.
- A **collection of sub-networks** forming larger networks.

### **Internet: A Network of Networks**

The entire **Internet** is essentially an interconnected web of private networks.

### **Subnets**

A **Subnet** is a smaller section within a network, designed to improve:

- **Traffic management**
- **Security**
- **Efficient resource allocation**

---
## **Repeater**

When data packets travel over long distances, their signal strength decays, leading to **data loss** or **failure in delivery**.

- A **Repeater** is a simple device designed to **regenerate and amplify signals** to maintain their strength.
- **Purpose**: Ensure reliable communication by eliminating signal degradation.
- **Use Case**: Long-distance communication, such as connecting two far-apart LANs.

---

## **Hub**

As networks grow, connecting every host to every other one becomes unmanageable. This is where **Hubs** come in.

- **Definition**: A **multiport repeater** that allows multiple devices to communicate by connecting to a central point.
- **Functionality**:
    1. Broadcasts incoming data to **all connected hosts**.
    2. Hosts check if the data is intended for them.
- **Drawback**:
    - **Inefficient and insecure**: All hosts receive all data, risking data breaches.
    - **High collision risk**: When multiple devices try to send data simultaneously.
- **Use Case**: Rarely used today due to its limitations, replaced by switches in most networks.

---

## **Bridge**

A **Bridge** connects two networks (or hubs), allowing selective communication between them.

- **Definition**: A device with two ports, used to **filter traffic between two network segments**.
- **Functionality**:
    1. Checks the destination IP address of data packets.
    2. Forwards packets **only if the destination is on the other side** of the bridge.
- **Key Feature**:
    - **Learning capability**: Bridges learn which hosts are connected to each side, improving efficiency.
- **Use Case**:
    - Smaller networks where segmentation is needed to reduce traffic.
    - Predecessor to switches.

---

## **Switch**

A **Switch** is a smarter, more advanced version of a hub and bridge combined.

- **Definition**: A device that facilitates communication between hosts in a network by **individually identifying each host**.
- **Functionality**:
    1. Maintains a **MAC address table** to track devices connected to each port.
    2. Forwards data directly to the correct recipient (unicast) instead of broadcasting to all hosts.
- **Key Advantage**:
    - **Efficient communication**: Eliminates unnecessary traffic within the network.
    - **Supports full-duplex mode**: Devices can send and receive data simultaneously.
- **Limitation**: Operates only within a **single network (LAN)**.
- **Use Case**: Backbone of most modern local area networks (LANs).

![[Pasted image 20241217231626.png]]

---

## **Router**

While switches handle communication within a network, a **Router** enables communication **between networks**.

- **Definition**: A device that connects multiple networks and directs data packets to their destination using a process called **routing**.
- **Functionality**:
    1. Maintains a **routing table**: A list of known networks and their paths.
    2. Uses the **gateway address**: The router's IP address in each network, serving as the "way out" for hosts.
    3. **Filters traffic**: Enhances security by acting as a control point for incoming and outgoing data.
- **Key Features**:
    - Learns routes dynamically through protocols (e.g., OSPF, BGP).
    - Supports **NAT (Network Address Translation)** to enable private networks to communicate with the internet.
- **Use Case**:
    - Connecting LANs to WANs or the internet.
    - Facilitating traffic control in large networks.
- **Hierarchy**: Routers are the building blocks of the **Internet**, creating the global network by interconnecting smaller networks.

![[Pasted image 20241217231537.png]]

> [!question]  **Routing vs. Switching**
> - **Routing**: Moving data between networks.
> - **Switching**: Moving data within a network.

---

## **Additional Network Devices**

1. **Access Points (APs)**:
    
    - Extend wireless connectivity within a network.
    - Acts as a bridge between wired and wireless networks.
2. **Firewalls**:
    
    - Provide **security** by filtering traffic based on predefined rules.
    - Can be hardware or software-based.
3. **Load Balancers**:
    
    - Distribute incoming traffic across multiple servers for optimal performance and reliability.
4. **Layer 3 Switches**:
    
    - Combine the functionality of switches and routers.
    - Operate within a network but support routing features.
5. **IDS/IPS (Intrusion Detection/Prevention Systems)**:
    
    - IDS: Detects malicious activities and alerts administrators.
    - IPS: Actively prevents detected threats.
6. **Proxies**:
    
    - Intermediary devices that mask user identities and cache content for faster access.
7. **Virtual Switches and Routers**:
    
    - Software-based implementations of physical switches/routers for virtualized environments.

---
