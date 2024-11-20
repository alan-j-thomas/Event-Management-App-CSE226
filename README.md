
# CSE226 Event Management App

## Overview
The **Event Management App** is a simple yet powerful Android application designed to help users manage various aspects of an event, including managing venues, guests, events, and checklists. It uses a Room database to store event details, venues, and guest information, allowing users to add, edit, and view these details with ease.

This app is developed for the CSE226 course and incorporates core Android components such as RecyclerView, ViewModel, Room Database, and Android Jetpack libraries for efficient data management and a responsive user interface.

## Features
- **Manage Events**: Add and update event details, including the event name, date, time, and budget.
- **Venue Management**: Store and manage venue information for events, including location and description.
- **Guest Management**: Add, view, and edit guest details such as name, email, menu, venue, and event type.
- **Checklists**: Create and manage event-related tasks with checkboxes for easy tracking.
- **Notifications**: Receive notifications when guest details are updated.
- **Responsive UI**: Utilize RecyclerView to display and manage lists of guests, events, and venues.

## Technologies Used
- **Kotlin**: The primary programming language used for developing the app.
- **Room Database**: Used for local data storage (events, guests, venues).
- **LiveData & ViewModel**: Manage UI-related data lifecycle-consciously.
- **RecyclerView**: For displaying lists of events, venues, and guests.
- **Date and Time Pickers**: Used to select event date and time.
- **Notifications**: To alert users about guest details updates.
- **Coroutines**: For performing database operations on background threads.


## Setup Instructions

### Prerequisites
To run the app locally, ensure you have the following installed:
- **Android Studio**: Version 4.0 or above
- **Kotlin**: Version 1.3 or higher
- **Gradle**: Gradle 5.0 or higher

### Steps to Run the App

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/CSE226-Event-Management-App.git
   ```

2. **Open the project in Android Studio**:
   - Launch Android Studio.
   - Select **Open an existing Android Studio project** and navigate to the directory where you cloned the repository.

3. **Sync Gradle**:
   - Once the project is opened in Android Studio, click **Sync Now** to sync the project with Gradle files.

4. **Run the Application**:
   - Connect an Android device or start an emulator.
   - Click on the **Run** button (the green triangle) in Android Studio to build and run the app.

5. **Permissions**:
   - The app doesn’t require any special permissions for normal operation. Ensure your app has permissions for notifications if needed.

### Database Setup
The app uses a **Room Database** to manage event, guest, and venue data. On first launch, the app will automatically create the database for you. It uses a local SQLite database with **LiveData** and **suspend functions** for handling database queries.

## Key Classes
- **EventActivity**: Handles event-related operations such as adding and displaying events.
- **VenueActivity**: Allows the management of venue details.
- **AddEvent**: UI for adding new events.
- **AddVenue**: UI for adding new venues.
- **GuestDatabase**: Room database class for managing guest-related data.
- **EventDatabase**: Room database class for managing event-related data.
- **GuestAdapter**: RecyclerView adapter for displaying a list of guests.
- **EventAdapter**: RecyclerView adapter for displaying a list of events.
- **NotificationUtils**: A utility class for handling push notifications.

## How It Works
1. **Add Event**: Users can add an event with a name, date, time, and budget. This data is stored in the **Event Database**.
2. **Add Venue**: Venues can be added with their name, location, and description, and are saved in the **Venue Database**.
3. **Manage Guests**: Users can add and edit guest details, such as name, email, menu preference, venue, and type of event they are attending. Guests are stored in the **Guest Database**.
4. **Tasks and Checklists**: Users can create and manage checklists for the event, marking tasks as completed.
5. **Notifications**: The app sends notifications to inform users when a guest’s details are updated.

## Contributions
Feel free to fork this project, submit issues, or contribute improvements via pull requests.


