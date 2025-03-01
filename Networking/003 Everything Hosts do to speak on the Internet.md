---
tags:
  - "#Networking"
---

# Hosts Communicating on the Same Network

### **Scenario Overview:**

In this scenario, two hosts, A and B, are directly connected on the same network. Both hosts are equipped with:

- **NICs** (Network Interface Cards), each having a unique **MAC address**.
- An **IP address** and a **subnet mask**.

Host A needs to send data to Host B. It already knows Host B’s **IP address**, and since both hosts are on the same IP network (this is determined through **subnetting**), they can communicate directly.
### **Step-by-Step Communication Process:**

1. **Creating the L3 Header:** Host A wants to send data to Host B. It creates a **Layer 3 (L3) header** that includes:
    
    - **Source IP address** (Host A’s IP)
    - **Destination IP address** (Host B’s IP)
    
    This header is crucial for routing the packet at the network layer, but Host A still doesn’t know Host B’s **MAC address** (required for the next layer, L2).
    
2. **Resolving the MAC Address:** Since Host A knows Host B’s **IP address** but not the **MAC address**, it uses **ARP (Address Resolution Protocol)** to resolve the destination MAC address. ARP is a protocol used to map an IP address to a corresponding MAC address within the same local network.
    
3. **ARP Request:** Host A sends an **ARP request** to the network, asking, "Who has IP address X.X.X.X?" (the IP address of Host B). It includes **Host A’s own MAC address** in the request, so Host B knows where to send the response.
    
    - The **destination MAC address** in the ARP request is set to `ff:ff:ff:ff:ff:ff`, which is a **broadcast address** used to send the request to **every device** on the local network.
4. **ARP Response:** When Host B receives the ARP request, it checks its own IP address and recognizes that it matches the requested IP. Host B then sends an **ARP response** directly back to Host A. This response contains:
    
    - **Host B’s MAC address** (which Host A originally asked for).
        
    - This response is a **unicast** message (sent directly to Host A), unlike the broadcast ARP request.
        
5. **Caching ARP Information:** Both Host A and Host B will now store the MAC-to-IP mapping in their **ARP cache**. This cache holds these mappings so that subsequent communication doesn’t need another ARP request.
    
    - **ARP cache**: A table in each device that stores the IP-to-MAC mappings, allowing devices to quickly access this information without needing to send out ARP requests repeatedly.
6. **Data Transmission:** Now that Host A has Host B’s MAC address, it can complete the **Layer 2 (L2) header** and send the data. The packet now includes:
    
    - **Source MAC address** (Host A’s MAC)
    - **Destination MAC address** (Host B’s MAC)
    
    When the data arrives at Host B, the **L2 and L3 headers are stripped off**, and the remaining data is passed to the appropriate application layer for further processing.
    
7. **Response from Host B:** If Host B needs to send data back to Host A, it can do so using the same MAC-to-IP mapping in the **ARP cache**, without needing to perform another ARP lookup.
    
    Both hosts can now communicate efficiently as the ARP cache remains populated, allowing each subsequent packet to be sent directly to the correct destination using the appropriate MAC address.

---
# Hosts Communicating with a Router Between Them

### **Scenario Overview:**

Now, we introduce a router between Host A and Host B. In this setup:

- Host A and Host B are **on different networks**, each with its own subnet.
- The router serves as an intermediary to forward packets between these two different networks.
- Both Host A and Host B still have their own **NICs** (Network Interface Cards), each with a unique **MAC address** and an **IP address** (as well as a **subnet mask**).
### **Step-by-Step Communication Process:**

1. **Host A Creates the L3 Header:** Host A wants to send data to Host B. Since Host A and Host B are on different networks, Host A must send the data to the router, which will then forward the packet to Host B.
    
    - Host A creates a **Layer 3 (L3) header** containing:
        - **Source IP address** (Host A’s IP)
        - **Destination IP address** (Host B’s IP)
    
    At this point, Host A doesn’t know the router's **MAC address** (needed to complete the L2 header), so it must resolve it first.
    
2. **Resolving the Router’s MAC Address (ARP Request):** Host A checks if the destination IP (Host B’s IP) is within its own network. Since Host B is on a different network, Host A knows it must send the packet to the router. To do this, Host A must first resolve the router's **MAC address**.
    
    - Host A sends out an **ARP request** to resolve the router’s **MAC address**. This request asks, "Who has the router’s IP address?"
    - The ARP request is a **broadcast** message, so it is sent to all devices on Host A’s local network.
3. **Router’s ARP Response:** The router receives the ARP request and sees that it is the target. The router responds with its **MAC address** in an **ARP response**.
    
    - The response is a **unicast** message sent directly to Host A, providing the router’s MAC address.
4. **Updating the ARP Cache:**
    
    - Host A now stores the router’s **MAC address** in its **ARP cache** for future use, ensuring it doesn’t need to send another ARP request for subsequent packets to the router.
5. **Sending Data to the Router:** Host A now constructs the **Layer 2 (L2) header** with:
    
    - **Source MAC address** (Host A’s MAC)
    - **Destination MAC address** (Router’s MAC)
    
    Host A sends the data packet to the router, which is responsible for forwarding it to the correct destination.
    
6. **Router Forwards the Packet:** The router examines the **Layer 3 (L3) header** (the **destination IP address**) and determines that the packet is intended for Host B, which is located on a different network.
    
    - The router strips off the **Layer 2 and Layer 3 headers** and forwards the data based on the **destination IP address**.
    - The router then constructs a new **L3 header** with:
        - **Source IP address** (the router’s own IP address on the destination network)
        - **Destination IP address** (Host B’s IP address)
    
    Additionally, the router must resolve **Host B’s MAC address** using **ARP** on the destination network.
    
7. **Resolving Host B’s MAC Address:** The router checks its own **ARP cache** to see if it already knows Host B’s MAC address. If not, the router sends out another **ARP request** to the destination network.
    
    - Host B responds with its MAC address, allowing the router to construct the **L2 header** for forwarding the data.
8. **Router Sends Data to Host B:** The router now sends the data packet to Host B with:
    
    - **Source MAC address** (router’s MAC address on the destination network)
    - **Destination MAC address** (Host B’s MAC address)
    
    When Host B receives the data, it strips off the **L2 and L3 headers**, and the data is passed to the application layer for further processing.
    
9. **Response from Host B:** If Host B needs to send data back to Host A, it can do so without involving the router, as Host B has Host A’s **IP and MAC address** from the initial communication.
    
    - Host B uses its **ARP cache** to find Host A’s MAC address and constructs the appropriate L2 and L3 headers to send the data back.
    
    If the data needs to go through the router (i.e., Host B’s response is meant for a host on another network), the process will repeat, with the router once again forwarding the packet between the two networks.

---

>[!info] **Key Concepts to Note:**
> - **Router:** A device that connects multiple networks, responsible for forwarding packets between them based on the destination **IP address**.
> - **ARP:** Address Resolution Protocol, which resolves IP addresses to MAC addresses, allowing communication between devices on the same network.
> - **ARP Cache:** A table that stores IP-to-MAC address mappings, allowing devices to quickly access this information and avoid sending out ARP requests unnecessarily.
> - **Broadcast and Unicast:**
>     - **Broadcast:** A message sent to all devices on the local network.
>     - **Unicast:** A message sent to a specific device (such as the ARP response from the router).


