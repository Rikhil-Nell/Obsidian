---
tags: []
---
The primary purpose of networking is to enable two or more hosts to exchange data efficiently over a medium, whether wired or wireless. For this to happen, hosts must follow a standardized set of rules to ensure that the data is transmitted, routed, and received correctly.

To organize and simplify these rules, they are divided into a layered framework called the **OSI (Open Systems Interconnection) Model**. This model consists of **7 layers**, each with a specific function in the process of data communication, from the physical transmission of bits to the interpretation of data by applications.


![[OSI Model.excalidraw|1000|center]]


By breaking down networking into these layers, the OSI model ensures interoperability between devices and technologies from different vendors while providing a clear structure for understanding complex network operations.

---
## **Physical Layer (L1):**

- **Purpose**: Transmits raw bits (1s and 0s) over a physical medium. Converts digital signals into physical signals and vice versa.
- **Examples**:
    - **Wired**: Ethernet cables, coaxial cables, fiber optics.
    - **Wireless**: Wi-Fi, Bluetooth, satellite.
- **Devices**:
    - **Repeater**: Amplifies or regenerates weak signals to extend the range.
    - **Hub (Multiport Repeater)**: A basic device that replicates and forwards data to all connected devices without understanding the data.

---
## **Data Link Layer (L2):**

- **Purpose**: Ensures reliable communication between two directly connected devices (NIC-to-NIC communication). Breaks data into **frames** and handles addressing via **MAC addresses**.
- **Key Features**:
    - **MAC Addressing**:
        - A 48-bit unique identifier for each NIC.
        - Represented in hexadecimal (e.g., `AA:BB:CC:DD:EE:FF`).
    - **Error Detection**: Uses techniques like CRC (Cyclic Redundancy Check) to detect errors in frames.
    - **Flow Control**: Ensures data is sent at a rate the receiver can handle.
    - **Frame Management**: Adds headers and trailers to data to create frames for transmission.
- **Devices**:
    - **Network Interface Card (NIC)**: Hardware enabling a device to connect to a network (wired or wireless).
    - **Switch**: Operates at L2 to forward frames intelligently using MAC address tables.
    - **Access Points**: Bridge wireless devices to a wired network at L2.
- **Limitations**: MAC addresses work well for local communication but are unsuitable for large-scale networks spanning multiple routers.

---
## **Network Layer (L3):**

- **Purpose**: Enables end-to-end communication across multiple networks. Uses **IP addresses** for identifying devices globally and routing packets to their destination.
- **Key Features**:
    - **IP Addressing**:
        - IPv4: 32-bit address represented in 4 octets (e.g., `192.168.1.1`).
        - IPv6: 128-bit address designed to overcome IPv4 limitations.
    - **Routing**:
        - Determines the best path to the destination host using routing protocols (e.g., OSPF, BGP).
    - **Packet Forwarding**: Moves packets from one network to another using routers.
    - **Encapsulation**: Adds an IP header to the data to form **packets**.
- **Devices**:
    - **Routers**: Interconnect different networks and forward packets based on IP addresses.
    - **Hosts**: Any device with an IP address (e.g., computers, phones).
- **Limitations**: IP addressing doesn't provide details on how devices are physically connected (handled by L2).

---
## **Why Use IP if You Have MAC (and Vice Versa)?**

- **MAC Addresses**:
    
    - Operate at the **local level** (within the same network).
    - Unique to each device’s NIC but **not routable across networks**.
    - Used by switches for delivering frames between devices in the same LAN.
- **IP Addresses**:
    
    - Operate at the **global level** (across multiple networks).
    - Hierarchical structure (network and host portions) allows scalable routing.
    - Required for end-to-end communication, even if devices are on different LANs.
- **Why Both?**:
    
    - MAC addresses are fixed (hardware-based) and work well for **local identification**.
    - IP addresses are flexible (software-based) and work well for **global identification**.
    - Together, they form a layered system:
        - **L2**: Finds the next hop (e.g., switch).
        - **L3**: Finds the final destination.

### **Example: Traversal of a Data Packet**

Let’s say a user on Computer A (IP: `192.168.1.2`, MAC: `AA:AA:AA:AA:AA:AA`) wants to send a packet to Computer B (IP: `10.0.0.5`, MAC: `BB:BB:BB:BB:BB:BB`) over the internet. Here’s how it works step by step:

1. **Inside the Local Network** (L2 + L3):
    
    - Computer A sends a packet destined for `10.0.0.5` with the IP header indicating the target IP.
    - The packet is encapsulated in a frame with:
        - Source MAC: `AA:AA:AA:AA:AA:AA`.
        - Destination MAC: MAC address of the local router (e.g., `CC:CC:CC:CC:CC:CC`).
    - Switches forward the frame within the LAN based on MAC addresses until it reaches the router.
2. **At the Router** (L3 Operation):
    
    - The router strips the L2 header and examines the IP address `10.0.0.5`.
    - It consults its routing table to determine the next hop.
    - The router re-encapsulates the packet with a new L2 header:
        - Source MAC: The router’s MAC (e.g., `DD:DD:DD:DD:DD:DD`).
        - Destination MAC: The next hop’s MAC (e.g., another router).
3. **Across Multiple Networks**:
    
    - The packet hops between multiple routers.
    - Each router uses the IP header to determine the next hop and updates the L2 header for that specific hop.
4. **At the Destination Network**:
    
    - The last router in the path identifies that `10.0.0.5` belongs to its local network.
    - It encapsulates the packet in a frame with:
        - Source MAC: The router’s MAC.
        - Destination MAC: `BB:BB:BB:BB:BB:BB` (Computer B’s MAC).
5. **Delivery to Computer B**:
    
    - The switch in Computer B’s local network forwards the frame based on the destination MAC address.
    - Computer B receives the packet, strips the L2 header, and processes the IP payload.

> [!info]   **Why This Matters**
> - **IP Addresses** guide the packet across multiple networks to the correct destination network.
> - **MAC Addresses** ensure that the packet reaches the correct device within a specific local network at each hop.


### **ARP (Address Resolution Protocol)**

#### **What is ARP?**

The **Address Resolution Protocol (ARP)** is a network protocol used to map an **IP address** (Layer 3 address) to a **MAC address** (Layer 2 address). This process is essential for devices in a local network (e.g., Ethernet) to communicate with each other, as data at the physical layer requires MAC addresses to transmit frames.
#### **Why is ARP Necessary?**

- **IP Routing:** At Layer 3 (Network Layer), communication uses IP addresses to identify devices.
- **Frame Delivery:** At Layer 2 (Data Link Layer), devices communicate using MAC addresses for hop-to-hop delivery.
- ARP bridges the gap between these two layers by resolving IP addresses into corresponding MAC addresses.

For example:

- A device knows it must send data to `192.168.1.10`, but it needs the **MAC address** of the target device to send the Ethernet frame. ARP resolves this for the device.
#### **How Does ARP Work?**

1. **Request Phase:**
    
    - A device (e.g., Host A) broadcasts an **ARP Request** packet on the network, asking:
        - "Who has this IP address (e.g., `192.168.1.10`)? Tell me your MAC address."
    - This ARP Request is sent to all devices in the local network as a broadcast frame.
2. **Reply Phase:**
    
    - The device with the matching IP address (e.g., Host B) responds with an **ARP Reply**, which contains its MAC address.
    - This response is unicast back to the requesting device (Host A).
3. **Caching:**
    
    - The requesting device stores the IP-to-MAC mapping in its **ARP Cache** to avoid repeated ARP Requests for the same IP address.
#### **Types of ARP**

1. **Standard ARP:** Resolves IPv4 to MAC addresses in a local network.
2. **Reverse ARP (RARP):** Maps MAC addresses to IP addresses (used in older systems for diskless clients).
3. **Proxy ARP:** A router responds to ARP Requests on behalf of another device (useful in complex networks).
4. **Gratuitous ARP:** A device sends an unsolicited ARP Reply to announce its own IP-to-MAC mapping (used for updates or conflict detection).
#### **Example of ARP in Action**

Imagine Host A (`192.168.1.1192.168.1.1192.168.1.1`) wants to send data to Host B (`192.168.1.10192.168.1.10192.168.1.10`):

1. Host A checks its ARP Cache for `192.168.1.10192.168.1.10192.168.1.10.` If not found:
    - It sends an ARP Request: "Who has `192.168.1.10192.168.1.10192.168.1.10`?"
2. Host B receives the broadcast, recognizes its IP, and sends an ARP Reply: "I have `192.168.1.10192.168.1.10192.168.1.10.` My MAC address is `00:1A:2B:3C:4D:5E00:1A:2B:3C:4D:5E00:1A:2B:3C:4D:5E`."
3. Host A updates its ARP Cache and uses this MAC address to send the data frame.

---
## **Transport Layer (L4):**

- **Purpose**: Facilitates service-to-service communication, enabling multiple applications (e.g., a browser, game, and chat app) to operate simultaneously on the same device. Uses **ports** to distinguish data streams.
    
- **Key Features**:
    
    - **Ports**:
        - Range: 0 to 65535, divided into TCP (reliable) and UDP (efficient) categories.
        - **Server Ports**: Servers listen for incoming requests on predefined ports (e.g., HTTP on port 80, HTTPS on port 443).
        - **Client Ports**: Clients select random ephemeral ports for each connection.
        - Port reuse allows multiple instances of the same service (e.g., multiple browser tabs) to function correctly.
    - **Connection Management**:
        - **TCP (Transmission Control Protocol)**: Ensures reliability via error checking, retransmission, and acknowledgments.
        - **UDP (User Datagram Protocol)**: Offers lower latency without guaranteeing delivery, ideal for real-time applications like video streaming or gaming.
    - **Communication**:
        - Data sent from the client is received by the server on its listening port.
        - Responses from the server return to the client's originating port, ensuring correct delivery.
- **Devices**:
    
    - **Hosts**: All networked devices that use ports for service-to-service communication.
    - **Firewalls**: Manage and filter port access to enhance security.
- **Limitations**:
    
    - Relies on the underlying **Network Layer (L3)** for routing and addressing.
    - Does not inherently provide encryption or authentication (addressed by higher-layer protocols like TLS).

---
## **The Forgotten Layers (L5-L7):**

The upper layers of the OSI model—**Session (L5)**, **Presentation (L6)**, and **Application (L7)**—are often grouped under the Application Layer in the modern TCP/IP model. This shift reflects a pragmatic approach to networking where the distinctions between these layers became less critical or were absorbed into broader functionalities.

---
## **Session Layer (L5):**

- **Purpose**:  
    Facilitates user-to-user communication by managing and distinguishing between multiple user sessions, ensuring consistent interactions across systems.
    
- **Historical Context**:
    
    - **Mainframe Era**:
        - The Session Layer was crucial during the mainframe era, where **dumb terminals** (simple monitors and keyboards) connected to compute units for processing.
        - It enabled communication between users on **different mainframes**, ensuring each session remained distinct.
    - **Linux Origins**:
        - Early Linux systems also leveraged session-based communication for supporting multi-user interactions.
- **Key Features**:
    
    - **Session Management**:
        - Maintains distinct user sessions, ensuring interactions and data exchange remain isolated and consistent.
        - Crucial for handling multi-user and multi-system communication effectively.
    - **Modern Implementation**:
        - HTTP cookies are a contemporary tool for managing sessions in a stateless protocol like HTTP.
- **HTTP Cookies**:
    
    - **What They Are**:
        - Arbitrary text strings that store user-specific information and facilitate session persistence.
    - **Use Case**:
        - For example, a user connected to their home Wi-Fi with their phone and communicating with a server might change to a coffee shop or school Wi-Fi.
        - In such cases, the user's **IP address changes**, which could disrupt their session.
        - **HTTP cookies solve this problem** by storing a unique identifier on the user’s device. The cookie allows the server to recognize the user and recall their session, even when the network changes, saving the user from needing to log in again.
    - **How They Work**:
        - Cookies, stored on the client’s device, are sent with every request to the server, enabling the server to maintain session continuity.
- **Devices**:
    
    - **Servers**: Store and interpret cookies for session tracking.
    - **Clients**: Retain cookies locally and include them in subsequent requests to the server.
- **Limitations**:
    
    - Stateless protocols like HTTP require explicit mechanisms (e.g., cookies) for session management.
    - Poor implementation or insufficient security can lead to vulnerabilities such as session hijacking or unauthorized access.

---
## **Presentation Layer (L6):**

- **Purpose**:  
    Translates raw data (1's and 0's) into a human-readable format or a specific data structure that applications can understand.
    
- **Key Features**:
    
    - **Data Interpretation**:
        - Decides how to group and interpret raw binary data. Examples include:
            - **BASE64**: Converts binary data into a textual representation for safe transmission over text-based protocols.
            - **Hexadecimal**: Represents binary data in base-16 format.
            - **Data Types**: Could interpret binary data as integers (e.g., long, int) or other specific types.
    - **ASCII Encoding (HTTP)**:
        - Groups raw binary data into sets of 8 bits (1 byte), where each byte represents an **ASCII character**.
        - Example:
          ``` rust
			01000111 -> G  
			01000101 -> E  
			01010100 -> T  
			00100000 -> (space)  
			00101111 -> /  
			01110011 -> s  
			01101001 -> i  
			01101101 -> m  
			```
		- This encoding mechanism allows browsers and servers to interpret HTTP requests (e.g., `GET /sim`).
- **Modern HTTP (HTTP/2, HTTP/3)**:
    
    - With advancements like **HTTP/2** and **HTTP/3**, encoding has shifted to more efficient binary protocols, reducing overhead and improving speed.
- **Devices**:
    
    - **Web Servers**: Translate incoming data into usable formats for applications.
    - **Clients (e.g., Browsers)**: Interpret server responses into visual or textual content for users.
- **Limitations**:
    
    - Requires proper configuration to match encoding formats between sender and receiver.
    - Mismatches in encoding can lead to data corruption or incorrect interpretations.

---
## **Application Layer (L7):**

- **Purpose**:  
    Provides the interface for users or applications to interact with the network. It sets up application commands and determines what to do with the interpreted characters from the **Presentation Layer (L6)**.
    
- **Key Features**:
    
    - **Interpreted Characters to Commands**:
        - Takes the decoded data from L6 and processes it as specific application commands.
        - For example, in HTTP:
            - **GET**: Requests a webpage or resource from a server.
            - **POST**: Sends data to the server, often used for forms or uploads.
            - **PUT**: Updates a resource on the server.
            - **DELETE**: Removes a resource from the server.
    - **Protocols**:
        - Popular protocols operating at this layer include:
            - **HTTP/HTTPS**: For web browsing and secure data transfer.
            - **SMTP**: For sending emails.
            - **FTP**: For file transfers.
            - **DNS**: For resolving domain names to IP addresses.
    - **Application-Specific Data Exchange**:
        - Enables software applications to send and receive data in a way they can process and display to the user.
- **Devices**:
    
    - **Web Browsers**: Interpret HTTP commands and render web pages for users.
    - **Email Clients**: Use SMTP or IMAP to send and receive emails.
    - **Cloud Apps**: Rely on APIs to execute application-specific commands.
- **Limitations**:
    
    - Relies heavily on proper functioning of lower layers to ensure data delivery and accuracy.
    - Protocol inefficiencies at this layer can lead to poor application performance.

---
### **Switch to TCP/IP**

Although HTTP is often considered an equivalent to OSI layers 5 to 7, it is only one of many protocols that interpret these layers differently. For example, **FTP** (File Transfer Protocol) does not implement a session layer at all. This means FTP has no inherent mechanism for maintaining user sessions, which is simply a quirk of its design. Such differences highlight the limitations of applying the rigid OSI model universally.

This is one of the reasons why modern networking has largely transitioned to the **TCP/IP model**, which consolidates the functions of layers 5, 6, and 7 into a single layer. By doing so, it offers greater flexibility, allowing applications to define their own protocols depending on the specific services they provide. For instance, HTTP is optimized for web communication, while FTP is tailored for file transfers. Both protocols serve different purposes and implement their layers differently, so enforcing the structure of the OSI model can be unnecessary and counterproductive.

![[TCP_IP and OSI.excalidraw|1000|center]]

The move to TCP/IP emphasizes practicality over strict abstraction. Unlike OSI, which envisions a clean, layered design, TCP/IP acknowledges that many real-world protocols are built with overlapping functionalities and don't adhere to neatly divided responsibilities.

However, it's essential to recognize that these models are not definitive rules but conceptual tools to help understand networking. The primary goal of these models is to teach **layers of abstraction**, where each layer focuses on specific responsibilities, such as addressing, routing, or data interpretation.

In practice, protocols are often hybrids that bend or combine these abstractions. So, while the OSI model is invaluable for learning the basics of networking, applying it rigidly to every protocol or real-world scenario may lead to confusion. Instead, think of it as a guide for understanding the modular nature of network communication rather than a strict blueprint.

The TCP/IP model reflects this adaptability, acknowledging that the way we implement network protocols depends on the needs of the application rather than forcing every technology into a predefined framework.

> [!note] **Overlapping Layers in Practice**  
> It's important to understand that network devices and protocols do not always strictly adhere to the layer they are traditionally assigned to. The abstraction of layers in models like OSI and TCP/IP is conceptual, and real-world implementations often blur these boundaries for efficiency or practicality. For example:  
> * **Firewalls**: Though primarily considered a Layer 3 or Layer 4 device (dealing with IP addresses and ports), firewalls can also inspect Layer 7 data (e.g., HTTP requests) for advanced filtering.  
> * **Switches**: Traditionally a Layer 2 device (working with MAC addresses), modern switches can also perform Layer 3 routing functions, making them hybrid devices.  
> * **HTTP Cookies**: While they fulfill the role of session management (Layer 5), cookies rely on data passed through layers below (e.g., TCP, IP) to function effectively.  
> * **ARP (Address Resolution Protocol)**: ARP primarily operates between Layer 2 (MAC addresses) and Layer 3 (IP addresses), translating IP addresses into MAC addresses. Its role spans both layers, showing how protocols can serve multiple purposes across traditional boundaries.  
> This fluidity exists because the ultimate goal of networking is to ensure seamless communication and functionality, even if it means crossing traditional layer boundaries. Think of the models as guidelines rather than rigid frameworks.


---
