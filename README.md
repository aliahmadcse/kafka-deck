# Apache Kafka Learning Deck

A comprehensive educational resource for learning Apache Kafka fundamentals, combining theoretical presentations with hands-on practical examples.

## 📚 What's Included

This repository contains a complete Kafka learning experience designed for workshops, training sessions, and self-study:

### 🎯 Educational Materials
- **`kafka-101.md`** - Fundamentals of Apache Kafka presentation (424 lines)
  - Data integration concepts and evolution
  - Kafka architecture and core components
  - Topics, partitions, and replication
  - Producers and consumers
  - Consumer groups and offsets
  
- **`kafka-102.md`** - Kafka Utilities and Tools (138 lines)
  - kafka-topics.sh usage
  - kafka-console-producer.sh and kafka-console-consumer.sh
  - kafka-consumer-groups.sh
  - Practical command examples

- **`questions-101.md`** - Q&A Session Material (85 lines)
  - Common Kafka questions and answers
  - Troubleshooting scenarios
  - Best practices discussions

### 💻 Practical Demo Code
The `kafka-demo/` directory contains a multi-module Spring Boot project demonstrating real-world Kafka usage:

- **Technology Stack**: Java 21, Spring Boot 3.3, Apache Kafka, Cassandra
- **Modules**:
  - `kafka-producer` - Basic Kafka producer with fake data generation
  - `kafka-consumer` - Consumer that writes to Cassandra database
  - `kafka-producer-wikimedia` - Real-time Wikimedia stream processing
  - `kafka-consumer-opensearch` - OpenSearch integration example
  - `commons` - Shared utilities and configurations

### 🎨 Visual Assets
- **`assets/`** - Presentation diagrams and images
  - Data integration visualizations
  - Kafka architecture diagrams
  - Consumer group illustrations
  - Partition and replication graphics
  
- **`graphics.excalidraw`** - Source diagrams for editing
- **`library/`** - Reusable Excalidraw components
- **`long-polling-vs-short-polling.pdf`** - Additional reference material

## 🚀 Getting Started

### Prerequisites
- **For Presentations**: Node.js and npm
- **For Demo Code**: Java 21, Maven, Docker & Docker Compose

### Setting Up the Presentation

1. **Install Marp CLI**
   ```bash
   npm install -g @marp-team/marp-cli
   ```

2. **Serve the Presentation**
   ```bash
   # Serve all presentations
   marp -s ./
   
   # Or serve specific presentation
   marp -s kafka-101.md
   ```

3. **Watch Mode for Development**
   ```bash
   marp -w kafka-101.md
   ```

4. **Export to PDF**
   ```bash
   marp kafka-101.md -o kafka-101.pdf --allow-local-files
   marp kafka-102.md -o kafka-102.pdf --allow-local-files
   ```

### Running the Demo Code

1. **Start Infrastructure**
   ```bash
   cd kafka-demo
   docker-compose up -d
   ```

2. **Build the Project**
   ```bash
   mvn clean install
   ```

3. **Run Producer Example**
   ```bash
   cd kafka-producer
   mvn spring-boot:run
   ```

4. **Run Consumer Example** (in another terminal)
   ```bash
   cd kafka-consumer
   mvn spring-boot:run
   ```

## 📖 Learning Path

1. **Start with Theory**: Read through `kafka-101.md` presentation
2. **Understand Tools**: Review `kafka-102.md` for practical utilities
3. **Hands-on Practice**: Set up and run the demo applications
4. **Explore Advanced Topics**: Try the Wikimedia streaming example
5. **Q&A Review**: Go through `questions-101.md` for common scenarios

## 🎯 Use Cases

- **Training Workshops** - Complete material for Kafka education sessions
- **Self-Study** - Structured learning path from basics to advanced
- **Reference** - Quick lookup for Kafka commands and configurations
- **Development** - Working code examples for integration projects

## 👨‍💻 Author

**Ali Ahmad**
- Website: [aliahmad.codes](https://aliahmad.codes)
- Repository: Educational content and demo applications for Apache Kafka

## 📄 License

This educational resource is provided for learning purposes. Check individual components for specific licensing terms.

---

*This repository serves as a complete learning ecosystem for Apache Kafka, from fundamental concepts to practical implementation.*
