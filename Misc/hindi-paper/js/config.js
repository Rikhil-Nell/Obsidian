const PAPER_CONFIG = {
  logo: "assets/logo.jpeg",
  school: "NEXTGEN SCHOOL",
  classLine: "FOURTH CLASS",
  assessment: "FORMATIVE ASSESSMENT-I",
  classNumber: "4",
  subject: "Hindi",
  totalMarks: "25",
  sections: [
    {
      id: 1,
      title: "सही उत्तर पर (✓) निशान लगाइए",
      marks: "1M",
      items: [
        { type: "tick", text: "जेम्स बहुत पढ़ाकू था" },
        { type: "tick", text: "जेम्स ने बिजली का आविष्कार किया" },
      ],
    },
    {
      id: 2,
      title: "दिए गए प्रश्नों के सही विकल्प चुनिए",
      marks: "2.5M",
      long: true,
      items: [
        {
          text: "जेम्स कहां का रहने वाला था?",
          options: ["क) अमेरिका", "ख) इंग्लैंड", "ग) फ्रांस"],
        },
        {
          text: "भाप में क्या होती है?",
          options: ["क) शक्ति", "ख) ठंडक", "ग) अग्नि"],
        },
        {
          text: "कोयल कैसे गीत गाती है?",
          options: ["क) खुशी के", "ख) मस्ती के", "ग) रसीले"],
        },
        {
          text: "कविता के अनुसार जल से क्या भर रहे हैं?",
          options: ["क) कुएं तालाब", "ख) पोखर ताल", "ग) नदी नाले"],
        },
        {
          text: "जानवरों की रक्षा के लिए क्या बनी है?",
          options: ["क) संस्थाएँ", "ख) सभाएँ", "ग) घर"],
        },
      ],
    },
    {
      id: 3,
      title: "दिए गए शब्दों के एक समान अर्थ शब्द लिखिए",
      marks: "1M",
      items: [
        { type: "answer", text: "पर्वत" },
        { type: "answer", text: "बादल" },
      ],
    },
    {
      id: 4,
      title: "दिए गए वाक्यों में क्रिया शब्द को रेखांकित कीजिए",
      marks: "1M",
      items: [
        { text: "माधव आम खाता है" },
        { text: "मोहन किताब पढ़ता है" },
      ],
    },
    {
      id: 5,
      title: "दिए गए शब्दों का बहुवचन लिखिए",
      marks: "1M",
      items: [
        { type: "answer", text: "सवारी" },
        { type: "answer", text: "यात्रा" },
      ],
    },
    {
      id: 6,
      title: "सही जगह पर ऋ की मात्रा लगाइए",
      marks: "1M",
      items: [{ text: "गह" }, { text: "वक्ष" }],
    },
    {
      id: 7,
      title: "का /की/ के /को को भरकर वाक्य पूरा कीजिए",
      marks: "2M",
      items: [
        {
          type: "blank",
          text:
            "दोनों बच्चे रेलगाड़ी <span class=\"fill-blank\"></span> देख रहे थे।",
        },
        {
          type: "blank",
          text: "उसने चाचा <span class=\"fill-blank\"></span> बात ना सुनी।",
        },
        {
          type: "blank",
          text:
            "इंग्लैंड <span class=\"fill-blank\"></span> एक शहर में एक गरीब बालक रहता था।",
        },
        {
          type: "blank",
          text:
            "जेम्स वाट ने भाप <span class=\"fill-blank\"></span> इंजन बनाया।",
        },
      ],
    },
    {
      id: 8,
      title: "सही वर्ण पर (ँ) या (ं) लगाकर शब्द पूरा कीजिए",
      marks: "1M",
      items: [{ text: "जगल" }, { text: "गाव" }],
    },
    {
      id: 9,
      title: "एक नया शब्द बनाइए",
      marks: "2M",
      items: [
        { type: "answer", text: "ल्ल" },
        { type: "answer", text: "क्क" },
      ],
    },
    {
      id: 10,
      title: "दिए गए शब्दों के अर्थ लिखिए",
      marks: "2M",
      items: [
        { type: "answer", text: "डगर", wide: true },
        { type: "answer", text: "विचित्र", wide: true },
        { type: "answer", text: "संदेश", wide: true },
        { type: "answer", text: "रक्षा", wide: true },
      ],
    },
    {
      id: 11,
      title: "कौन कहां रहता है सही मिलान कीजिए",
      marks: "2.5M",
      type: "matching",
      animals: [
        { image: "assets/matching/dog.jpg" },
        { image: "assets/matching/lion.jpg" },
        { image: "assets/matching/bee.jpg" },
        { image: "assets/matching/fish.jpg" },
        { image: "assets/matching/bird.jpg" },
      ],
      habitats: [
        { image: "assets/matching/lake.jpg" },
        { image: "assets/matching/nest.jpg" },
        { image: "assets/matching/kennel.jpg" },
        { image: "assets/matching/beehive.jpg" },
        { image: "assets/matching/den.jpg" },
      ],
    },
    {
      id: 12,
      title: "किन्ही तीन प्रश्नों के उत्तर लिखिए",
      marks: "3M",
      long: true,
      type: "qa-blocks",
      items: [
        { text: "सृजन का संदेश कौन देता है ?" },
        { text: "दादी ने सकीना को किसके बारे में बताया ?" },
        { text: "जंगली पशु वन में क्यों रहते हैं?" },
        { text: "जंगल में रहने वाले पशु को क्या कहते हैं ?" },
      ],
      answerBlocks: 3,
      linesPerBlock: 3,
    },
    {
      id: 13,
      title: "Oral and Class Performance",
      marks: "5M",
      items: [],
    },
  ],
};
