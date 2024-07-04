MIT 6.824: https://pdos.csail.mit.edu/6.824/schedule.html

CMU 15-418/Stanford CS149: Parallel Computing https://csdiy.wiki/en/%E5%B9%B6%E8%A1%8C%E4%B8%8E%E5%88%86%E5%B8%83%E5%BC%8F%E7%B3%BB%E7%BB%9F/CS149/

Parallel Computer Architecture and Programming  http://15418.courses.cs.cmu.edu/spring2016/lectures

# Principles of Fault Tolerance
### Important Parts of the Paper: Principles of Fault Tolerance

1. **Error Detection**:
   - Fault tolerance begins with identifying errors that occur in the system.

2. **Damage Assessment**:
   - Evaluating the extent of system corruption caused by detected errors.

3. **Error Recovery**:
   - Techniques include **backward recovery** (reverting to a previous state) and **forward recovery** (constructing a new error-free state).

4. **Fault Treatment**:
   - Removing or **mitigating faults** to ensure **system continuity**.

5. **Design Fault Tolerance**:
   - Incorporating mechanisms to **handle faults within the system's design**.

6. **N-Version Programming**:
   - **Running multiple independent versions** of a program and **using a majority voting mechanism to determine the correct output**.

7. **Recovery Blocks**:
   - Using **alternative procedures to achieve fault tolerance**.

8. **Software Implemented Fault Tolerance**:
   - Applying software techniques to handle hardware faults and ensure system reliability.

These principles form the basis for designing robust and reliable computing systems capable of handling both expected and unexpected faults.

### Definitions and Concepts

**Fault Definition:**
- A fault is an incorrect state in a system component or its design, leading to system errors or failures.

**Related Concepts:**
- **Error**: The manifestation of a fault, leading to an incorrect internal state.
- **Failure**: When the system's behavior deviates from its specification due to an error.
- **Fault Tolerance**: The system's ability to continue operating correctly in the presence of faults.
