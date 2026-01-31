# Java Core Algorithms & Concurrency Portfolio

![Java](https://img.shields.io/badge/Java-17%2B-orange) ![Maven](https://img.shields.io/badge/Build-Maven-blue) ![Testing](https://img.shields.io/badge/Testing-JUnit_5-green)

A comprehensive collection of high-performance Java implementations focusing on **multi-threaded processing**, **custom data structures**, and **algorithmic optimization**. This repository demonstrates a deep understanding of core Computer Science fundamentals, build automation (Maven), and Test-Driven Development (TDD).

## 📂 Project Overview

### 1. Concurrency & Parallel Processing (`/Maven-Projects`)
Maven-based implementations designed to utilize multi-core architecture for performance optimization.
* **Parallel Max Search:** A multi-threaded search algorithm that partitions large datasets to locate maximum values efficiently. Features robust input parsing for non-integer streams and parallel array processing.
* **Parallel Merge Sort:** A recursive divide-and-conquer sorting algorithm optimized for parallel execution using thread pools.
* **Word Frequency Analysis (HashTable):** A custom Hash Table implementation (Key: `String`, Value: `Object`) featuring:
    * Custom collision resolution logic.
    * A manually implemented `Iterator` for key traversal.
    * **Application:** A frequency analysis tool that processes text streams to count word occurrences (e.g., `aa: 1`, `ab: 3`).

### 2. Core Data Structures (`/Core-Data-Structures`)
Low-level, custom implementations of standard Java collections to demonstrate memory management and pointer logic (no built-in collections used).
* **Dynamic Array:** A manual implementation of a resizable array (similar to `ArrayList`) managing dynamic memory allocation and resizing logic.
* **Binary Search Tree:** A recursive implementation of a BST including in-order traversal and node manipulation.
* **Search Trees:** Optimized tree structures for efficient data retrieval.

## 🛠 Technical Highlights

* **Build Automation:** All major modules are structured as **Maven** projects with `pom.xml` configuration for dependency management and build lifecycles.
* **Unit Testing:** Comprehensive **JUnit** test coverage ensures logic stability and edge-case handling (e.g., empty streams, invalid inputs).
* **Clean Code:** Follows strict encapsulation and package naming conventions (`cz.cuni.mff...`).

## 🚀 How to Run

To execute the unit tests for the Concurrency modules:

```bash
# Navigate to a specific project module
cd Maven-Projects/PARA_MERGE_SORT

# Clean and run tests using Maven
mvn clean test
