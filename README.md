# Swipe Android App

## Overview
The Swipe Android App is a product management application built for Android devices. 
It provides a seamless user experience for viewing and managing products, incorporating essential 
features like product search, easy navigation, and efficient data entry.

## Screens



### Product Listing Screen
- **Features:**
  - Display a comprehensive list of products sourced from the Swipe API endpoint.
  - User-friendly product search functionality.
  - Smooth scrolling through the product list.
  - Quick navigation to the Add Product screen via a dedicated button.
  - Dynamic loading of product images from provided URLs or default images when URLs are empty.
  - Offline caching using Room for uninterrupted access to cached data in the absence of internet connectivity.

- **API Integration:**
  - GET Request: https://app.getswipe.in/api/public/get
    
### Add Product Screen (BottomSheetDialogFragment)
- **Features:**
  - Streamlined process for adding new products with the following details:
    - Product type selection from a curated list of options.
    - Input fields for product name, selling price, and tax rate ensure comprehensive data entry.
  - Input field validation guarantees accuracy in data entry, including product type selection, non-empty product name, and proper formatting for selling price and tax.

- **API Integration:**
  - POST Request: https://app.getswipe.in/api/public/add

- **User Interface:**
  - Employs an intuitive and visually appealing interface to enhance the user experience.

- **Feedback to the User:**
  - Provides clear and concise feedback to users upon completing actions, utilizing dialogs(Toast Messages).

## Getting Started
Follow these steps to set up the Swipe Android App locally on your development environment.

1. Clone the repository:
    ```
   git clone https://github.com/p4puniya/Swipe-Android-Assignment.git
    ```

2. Open the project in Android Studio.
3. Build and run the app on your Android device or emulator.

## Dependencies
List of major dependencies used in the project:

- [Retrofit-2.9.0](https://square.github.io/retrofit/)
- [Glide-4.16.0](https://github.com/bumptech/glide)
- [Coroutines-1.7.1](https://developer.android.com/kotlin/coroutines#dependency)
- [Room-DB with Androidx-2.6.1](https://developer.android.com/training/data-storage/room#setup)
