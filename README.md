# Trivia_Parking_Space

## Overview
Trivia Parking Space is a management application designed for students, faculty, and guests to handle parking services efficiently. It offers tiered service levels and a smooth flow for sign-up, parking, and exit processes.

## Key Features

* **User Categories**:

  * Students and Faculty can register with a unique ID and preload wallet balance.
  * Guests can use a simplified parking option without registration.
* **Service Levels**:

  * **Green Line**: Basic parking.
  * **Gold Line**: Parking plus car wash.
  * **Orange Line**: Parking plus vehicle inspection.
* **Wallet Integration**:

  * Registered users load money once and pay automatically on exit.
  * Future plans include viewing remaining balance and recharging wallet.
* **Guest Flow**:

  * Enter vehicle number and choose a service level.
  * No premium unless switching lines beyond registration.
* **Parking Operations**:

  * Park with or without user ID.
  * Exit flow calculates bill based on chosen service and duration (future enhancement).
* **Additional Services**:

  * Car wash and vehicle inspection integrated via decorator-like enhancements.

## Application Flow

1. **Sign-Up (Registered Users)**

   * Enter unique user ID, select type (Student or Faculty), load wallet balance.
   * Choose default parking line (Green/Gold/Orange).
   * Register one or more vehicles with number and name.
2. **Proceed to Parking Lot**

   * **Park Vehicle**:

     * With ID: system recognizes registration; switching lines may incur premium.
     * Without ID: enter vehicle number and choose line; treated as guest flow.
   * **Exit Vehicle**:

     * Enter registered vehicle number; system deducts bill from wallet and updates availability.
3. **Guest Parking**

   * Direct entry by vehicle number and chosen line; basic billing without wallet integration.

## Design Patterns

* **Singleton**: Ensures single instance of key managers throughout runtime.
* **Factory**: Creates billing strategy objects for different service levels.
* **Strategy**: Encapsulates distinct billing calculations (basic, premium, add-ons).
* **Adapter**: Unifies payment methods under a common interface.
* **Prototype**: Clones base models for users and vehicles when needed.
* **Decorator**: Layers extra services (e.g., car wash, safety checks) onto core parking service.
* **State**: Represents parking lot states (available, full) to guide parking decisions.
* **Observer**: Links parking lot status changes to user notifications or internal updates.
* **Facade**: Provides a simple interface for signup, parking, exiting, hiding underlying complexity.

## Future Enhancements

* Auto-generate unique user IDs within the platform.
* Display remaining wallet balance after each exit, and allow recharges.
* Charge based on parking duration and service level dynamically.
* Expand reporting dashboards for lot utilization and revenue insights.
* Integrate notifications (e.g., low wallet balance alerts).
* Allow online booking or reservation of parking slots ahead of arrival.
