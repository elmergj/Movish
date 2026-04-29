# Aggregate Specification: [User]

> **Definition:** High-level description of what this aggregate represents in the business world.

---

## 1. Aggregate Root & State (Properties)
The state of the aggregate is encapsulated. Only the Aggregate Root can be accessed from outside.

| Property  | Type / VO    | Mandatory | Business Description |
|:----------|:-------------|:----------|:---------------------|
| `id`      | `InternalId` | Yes       | Unique domain ID.    |
| `[Field]` | `[Type]`     | [Y/N]     | [Description]        |

---

## 2. Lifecycle & State Transitions
Visual representation of the status changes.



* **[STATUS_A]** → **[STATUS_B]**: Triggered by `methodName()`.
* **[STATUS_B]** → **[STATUS_C]**: Triggered by `anotherMethod()`.

---

## 3. Business Logic (Invariants & Methods)
Methods that protect the integrity of the aggregate.

* **`actionName(parameters)`**:
		* **Pre-conditions:** What must be true before?
		* **Post-conditions:** What changes after execution?
		* **Invariant:** e.g., "Total amount cannot exceed $10,000".

---

## 4. Domain Events
Events published to notify other parts of the system about changes.

* **`[AggregateName]UpdatedEvent`**: Published when...
* **`[AggregateName]CreatedEvent`**: Published when...

---

## 5. Internal Entities
If this aggregate contains other entities (that are not roots).

* **Entity [Name]**: (e.g., `OrderItem` inside `Order`).
		* **Rules:** Describe how it is managed by the root.