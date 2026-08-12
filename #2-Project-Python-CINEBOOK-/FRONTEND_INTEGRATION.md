# CINEBOOK - Frontend Integration Guide
## Connecting HTML, CSS, and JavaScript with Python Backend

---

## Table of Contents
1. [Overview](#overview)
2. [Architecture](#architecture)
3. [Frontend Structure](#frontend-structure)
4. [JavaScript & API Communication](#javascript--api-communication)
5. [Step-by-Step Integration](#step-by-step-integration)
6. [Complete Code Examples](#complete-code-examples)
7. [Debugging & Testing](#debugging--testing)

---

## Overview

This guide explains how to create a web interface (HTML/CSS/JS) that communicates with your Python Flask backend.

### How It Works:
```
User Input (HTML Form)
    ↓
JavaScript (Event Handling)
    ↓
API Call (fetch/axios)
    ↓
Python Flask Backend
    ↓
Data Processing
    ↓
JSON Response
    ↓
JavaScript (Update DOM)
    ↓
Display on HTML Page
```

---

## Architecture

### Backend → Frontend Flow:

```
┌─────────────────────────────────────┐
│   Python Backend (Flask)            │
│  ├─ Database (SQLite)               │
│  ├─ Business Logic                  │
│  └─ API Endpoints                   │
└──────────────┬──────────────────────┘
               │ (JSON responses)
               ↓
┌─────────────────────────────────────┐
│   API Routes (HTTP)                 │
│  ├─ GET /api/movies                 │
│  ├─ POST /api/book                  │
│  └─ GET /api/bookings               │
└──────────────┬──────────────────────┘
               │ (REST Communication)
               ↓
┌─────────────────────────────────────┐
│   Frontend (HTML/CSS/JS)            │
│  ├─ User Interface (HTML)           │
│  ├─ Styling (CSS)                   │
│  └─ Logic & API Calls (JS)          │
└─────────────────────────────────────┘
```

---

## Frontend Structure

### Folder Layout:

```
static/
├── index.html           # Main page (movies list)
├── booking.html         # Booking page
├── confirmations.html   # Confirmation page
│
├── css/
│   ├── style.css        # Main styles
│   ├── responsive.css   # Mobile responsive
│   └── animations.css   # Animations
│
└── js/
    ├── api.js          # API functions
    ├── app.js          # Main application logic
    ├── utils.js        # Utility functions
    └── events.js       # Event handlers
```

---

## JavaScript & API Communication

### Concept 1: Fetch API (Modern Standard)

```javascript
// Basic fetch structure
fetch('http://localhost:5000/api/movies', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json'
    }
})
.then(response => response.json())
.then(data => console.log(data))
.catch(error => console.error('Error:', error));
```

### Concept 2: API Response Format (JSON)

**Python sends:**
```json
{
    "success": true,
    "movies": [
        {
            "id": 1,
            "name": "Odyssey",
            "language": "English",
            "price": 295,
            "shows": ["10:00 AM", "2:00 PM", "6:00 PM"]
        }
    ]
}
```

**JavaScript processes it:**
```javascript
// Parse response
const movies = data.movies;

// Loop through movies
movies.forEach(movie => {
    console.log(movie.name); // "Odyssey"
    console.log(movie.price); // 295
});
```

### Concept 3: Sending Data to Backend

```javascript
// POST request with data
const bookingData = {
    movie_id: 1,
    show: "10:00 AM",
    seats: 2,
    name: "John Doe",
    phone: "9876543210"
};

fetch('http://localhost:5000/api/book', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(bookingData)
})
.then(response => response.json())
.then(data => {
    if (data.success) {
        console.log('Booking ID:', data.booking_id);
        alert('Booking successful!');
    }
})
.catch(error => console.error('Error:', error));
```

---

## Step-by-Step Integration

### Step 1: Enable CORS in Python Backend

**Why CORS is needed:**
- Frontend (localhost:5000 for HTML)
- Backend (localhost:5000 for Flask)
- Browsers block cross-origin requests for security

**Solution in app.py:**
```python
from flask import Flask
from flask_cors import CORS

app = Flask(__name__)
CORS(app)  # Allow cross-origin requests

# Rest of your code...
```

### Step 2: Create HTML Form

```html
<!-- static/index.html -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CINEBOOK - Movie Booking</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1>🎬 CINEBOOK</h1>
        <h2>Cinema Ticket Booking System</h2>
        
        <!-- Movies Container -->
        <div id="movies-container" class="movies-grid">
            <!-- Movies will be loaded here by JavaScript -->
        </div>
    </div>
    
    <!-- Modal for Booking -->
    <div id="booking-modal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h3>Book Your Tickets</h3>
            <form id="booking-form">
                <div class="form-group">
                    <label for="customer-name">Customer Name:</label>
                    <input type="text" id="customer-name" required>
                </div>
                
                <div class="form-group">
                    <label for="customer-phone">Phone Number:</label>
                    <input type="tel" id="customer-phone" required>
                </div>
                
                <div class="form-group">
                    <label for="show-select">Select Show:</label>
                    <select id="show-select" required></select>
                </div>
                
                <div class="form-group">
                    <label for="seats-input">Number of Seats (1-10):</label>
                    <input type="number" id="seats-input" min="1" max="10" required>
                </div>
                
                <button type="submit" class="btn-submit">Confirm Booking</button>
            </form>
        </div>
    </div>
    
    <script src="js/api.js"></script>
    <script src="js/app.js"></script>
</body>
</html>
```

### Step 3: Write JavaScript API Functions

```javascript
// static/js/api.js

const API_BASE = 'http://localhost:5000/api';

// Fetch all movies
async function fetchMovies() {
    try {
        const response = await fetch(`${API_BASE}/movies`);
        const data = await response.json();
        
        if (data.success) {
            return data.movies;
        }
    } catch (error) {
        console.error('Error fetching movies:', error);
    }
}

// Submit booking
async function submitBooking(bookingData) {
    try {
        const response = await fetch(`${API_BASE}/book`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(bookingData)
        });
        
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error submitting booking:', error);
    }
}

// Fetch all bookings
async function fetchBookings() {
    try {
        const response = await fetch(`${API_BASE}/bookings`);
        const data = await response.json();
        
        if (data.success) {
            return data.bookings;
        }
    } catch (error) {
        console.error('Error fetching bookings:', error);
    }
}
```

### Step 4: Write Main Application Logic

```javascript
// static/js/app.js

let selectedMovie = null;
let movies = [];

// Initialize application
async function initApp() {
    console.log('Initializing CINEBOOK...');
    
    // Fetch and display movies
    movies = await fetchMovies();
    
    if (movies) {
        displayMovies(movies);
    }
    
    // Setup event listeners
    setupEventListeners();
}

// Display movies in UI
function displayMovies(movies) {
    const container = document.getElementById('movies-container');
    container.innerHTML = '';
    
    movies.forEach(movie => {
        const movieCard = document.createElement('div');
        movieCard.className = 'movie-card';
        movieCard.innerHTML = `
            <div class="movie-header">
                <h3>${movie.name}</h3>
                <span class="language-badge">${movie.language}</span>
            </div>
            <div class="movie-details">
                <p><strong>Price:</strong> Rs. ${movie.price}</p>
                <p><strong>Available Shows:</strong></p>
                <div class="shows-list">
                    ${movie.shows.map((show, idx) => `
                        <span class="show-badge">${show}</span>
                    `).join('')}
                </div>
            </div>
            <button class="btn-book" onclick="openBookingModal(${movie.id}, '${movie.name}', ${movie.price}, ${JSON.stringify(movie.shows).replace(/"/g, '&quot;')})">
                Book Tickets
            </button>
        `;
        container.appendChild(movieCard);
    });
}

// Open booking modal
function openBookingModal(movieId, movieName, price, shows) {
    selectedMovie = {
        id: movieId,
        name: movieName,
        price: price,
        shows: shows
    };
    
    // Populate show dropdown
    const showSelect = document.getElementById('show-select');
    showSelect.innerHTML = '';
    shows.forEach((show, idx) => {
        const option = document.createElement('option');
        option.value = show;
        option.textContent = show;
        showSelect.appendChild(option);
    });
    
    // Show modal
    document.getElementById('booking-modal').style.display = 'block';
}

// Setup event listeners
function setupEventListeners() {
    // Booking form submission
    document.getElementById('booking-form').addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const name = document.getElementById('customer-name').value;
        const phone = document.getElementById('customer-phone').value;
        const show = document.getElementById('show-select').value;
        const seats = parseInt(document.getElementById('seats-input').value);
        
        // Validate phone (must be 10 digits)
        if (!/^\d{10}$/.test(phone)) {
            alert('Phone number must be 10 digits');
            return;
        }
        
        // Prepare booking data
        const bookingData = {
            movie_id: selectedMovie.id,
            show: show,
            seats: seats,
            name: name,
            phone: phone
        };
        
        // Submit booking
        const result = await submitBooking(bookingData);
        
        if (result.success) {
            alert(`Booking Confirmed!\nBooking ID: ${result.booking_id}\nTotal: Rs. ${result.total}`);
            closeBookingModal();
            document.getElementById('booking-form').reset();
        } else {
            alert(`Error: ${result.error}`);
        }
    });
    
    // Close modal button
    document.querySelector('.close').addEventListener('click', closeBookingModal);
    
    // Close modal when clicking outside
    window.addEventListener('click', (e) => {
        const modal = document.getElementById('booking-modal');
        if (e.target === modal) {
            closeBookingModal();
        }
    });
}

// Close booking modal
function closeBookingModal() {
    document.getElementById('booking-modal').style.display = 'none';
}

// Run on page load
document.addEventListener('DOMContentLoaded', initApp);
```

### Step 5: Write CSS Styling

```css
/* static/css/style.css */

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    min-height: 100vh;
    padding: 20px;
}

.container {
    max-width: 1200px;
    margin: 0 auto;
}

h1 {
    text-align: center;
    color: white;
    font-size: 2.5em;
    margin-bottom: 10px;
    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
}

h2 {
    text-align: center;
    color: rgba(255, 255, 255, 0.9);
    margin-bottom: 40px;
    font-size: 1.3em;
}

/* Movie Grid */
.movies-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
    gap: 20px;
    margin-bottom: 40px;
}

/* Movie Card */
.movie-card {
    background: white;
    border-radius: 12px;
    padding: 20px;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
    transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.movie-card:hover {
    transform: translateY(-10px);
    box-shadow: 0 15px 40px rgba(0, 0, 0, 0.3);
}

.movie-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 15px;
    border-bottom: 2px solid #667eea;
    padding-bottom: 10px;
}

.movie-header h3 {
    color: #333;
    font-size: 1.4em;
}

.language-badge {
    background: #667eea;
    color: white;
    padding: 4px 12px;
    border-radius: 20px;
    font-size: 0.8em;
    font-weight: bold;
}

.movie-details {
    margin: 15px 0;
}

.movie-details p {
    color: #555;
    margin: 8px 0;
    font-size: 0.95em;
}

.shows-list {
    display: flex;
    gap: 8px;
    flex-wrap: wrap;
    margin-top: 10px;
}

.show-badge {
    background: #f0f0f0;
    border: 1px solid #ddd;
    padding: 6px 12px;
    border-radius: 8px;
    font-size: 0.85em;
    font-weight: 500;
}

/* Buttons */
.btn-book {
    width: 100%;
    padding: 12px;
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    color: white;
    border: none;
    border-radius: 8px;
    font-size: 1em;
    font-weight: bold;
    cursor: pointer;
    transition: all 0.3s ease;
    margin-top: 15px;
}

.btn-book:hover {
    transform: scale(1.05);
    box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
}

.btn-book:active {
    transform: scale(0.98);
}

/* Modal */
.modal {
    display: none;
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.4);
    animation: fadeIn 0.3s ease;
}

@keyframes fadeIn {
    from { opacity: 0; }
    to { opacity: 1; }
}

.modal-content {
    background-color: white;
    margin: 5% auto;
    padding: 30px;
    border-radius: 12px;
    width: 90%;
    max-width: 500px;
    box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
    animation: slideDown 0.3s ease;
}

@keyframes slideDown {
    from {
        transform: translateY(-50px);
        opacity: 0;
    }
    to {
        transform: translateY(0);
        opacity: 1;
    }
}

.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
    cursor: pointer;
    transition: color 0.3s ease;
}

.close:hover {
    color: #000;
}

.modal-content h3 {
    color: #333;
    margin-bottom: 20px;
    font-size: 1.5em;
}

/* Form Styling */
.form-group {
    margin-bottom: 20px;
    display: flex;
    flex-direction: column;
}

.form-group label {
    color: #333;
    font-weight: 600;
    margin-bottom: 8px;
}

.form-group input,
.form-group select {
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 6px;
    font-size: 1em;
    transition: border-color 0.3s ease;
}

.form-group input:focus,
.form-group select:focus {
    outline: none;
    border-color: #667eea;
    box-shadow: 0 0 5px rgba(102, 126, 234, 0.3);
}

.btn-submit {
    padding: 12px;
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    color: white;
    border: none;
    border-radius: 6px;
    font-size: 1em;
    font-weight: bold;
    cursor: pointer;
    transition: all 0.3s ease;
}

.btn-submit:hover {
    transform: scale(1.05);
}

.btn-submit:active {
    transform: scale(0.98);
}

/* Responsive Design */
@media (max-width: 768px) {
    .movies-grid {
        grid-template-columns: 1fr;
    }
    
    h1 {
        font-size: 2em;
    }
    
    .modal-content {
        width: 95%;
        margin: 20% auto;
    }
}
```

---

## Complete Code Examples

### Python Backend (app.py) - Complete Version

```python
from flask import Flask, jsonify, request
from flask_cors import CORS
from src.models import Movie, Booking, BookingManager
from src.validators import Validator
import json

app = Flask(__name__)
CORS(app)

manager = BookingManager()

# Initialize movies
movies_data = [
    (1, "Odyssey", "English", 295, ["10:00 AM", "2:00 PM", "6:00 PM"]),
    (2, "The Conjuring", "English", 215, ["11:00 AM", "3:00 PM", "7:00 PM"]),
    (3, "The Conjuring 2", "English", 233, ["9:00 AM", "5:00 PM", "11:00 PM"])
]

for m_id, name, lang, price, shows in movies_data:
    movie = Movie(m_id, name, lang, price, shows)
    manager.add_movie(movie)

# API Endpoints

@app.route('/', methods=['GET'])
def home():
    return jsonify({'message': 'CINEBOOK API is running!'})

@app.route('/api/movies', methods=['GET'])
def get_movies():
    """Get all available movies"""
    movies = manager.get_all_movies()
    return jsonify({
        'success': True,
        'movies': [
            {
                'id': movie.id,
                'name': movie.name,
                'language': movie.language,
                'price': movie.price,
                'shows': movie.shows
            }
            for movie in movies.values()
        ]
    })

@app.route('/api/book', methods=['POST'])
def create_booking():
    """Create a new booking"""
    data = request.json
    
    # Validate inputs
    if not data or 'movie_id' not in data:
        return jsonify({'success': False, 'error': 'Missing movie_id'}), 400
    
    movie_id = data.get('movie_id')
    if not Validator.validate_movie_id(int(movie_id), manager.movies.keys()):
        return jsonify({'success': False, 'error': 'Invalid movie ID'}), 400
    
    seats = data.get('seats')
    if not Validator.validate_seats(int(seats)):
        return jsonify({'success': False, 'error': 'Invalid seat count (1-10)'}), 400
    
    name = data.get('name')
    if not Validator.validate_customer_name(name):
        return jsonify({'success': False, 'error': 'Invalid name'}), 400
    
    phone = data.get('phone')
    if not Validator.validate_phone(str(phone)):
        return jsonify({'success': False, 'error': 'Invalid phone number'}), 400
    
    # Create booking
    movie = manager.movies[int(movie_id)]
    booking = Booking(
        name,
        phone,
        movie.name,
        data.get('show'),
        int(seats),
        movie.price
    )
    
    manager.add_booking(booking)
    
    return jsonify({
        'success': True,
        'booking_id': booking.booking_id,
        'total': booking.total_price,
        'message': 'Booking confirmed!'
    })

@app.route('/api/bookings', methods=['GET'])
def get_bookings():
    """Get all bookings"""
    bookings = manager.get_all_bookings()
    return jsonify({
        'success': True,
        'bookings': [
            {
                'booking_id': b.booking_id,
                'name': b.name,
                'phone': b.phone,
                'movie': b.movie,
                'show': b.show,
                'seats': b.seats,
                'total': b.total_price
            }
            for b in bookings
        ]
    })

@app.errorhandler(404)
def not_found(error):
    return jsonify({'success': False, 'error': 'Endpoint not found'}), 404

@app.errorhandler(500)
def server_error(error):
    return jsonify({'success': False, 'error': 'Server error'}), 500

if __name__ == '__main__':
    app.run(debug=True, port=5000)
```

---

## Debugging & Testing

### 1. Check if Backend is Running

```bash
# Terminal 1: Start Flask server
python app.py

# You should see:
# * Running on http://127.0.0.1:5000
```

### 2. Test API in Browser

```
http://localhost:5000/api/movies
```

Should show JSON response with movies.

### 3. Browser Console Debugging

```javascript
// Open Chrome DevTools: F12 or Ctrl+Shift+I

// Check console for errors
console.log('Test message');

// Check network tab to see API calls
// Check Application tab for local storage
```

### 4. Common Issues & Solutions

**Issue: CORS Error**
```
Error: Access to XMLHttpRequest blocked by CORS policy
```
**Solution:** Ensure Flask-CORS is installed and imported
```python
from flask_cors import CORS
CORS(app)
```

**Issue: 404 Not Found**
```
Error: GET /api/movies 404
```
**Solution:** Check route definitions in app.py, ensure spelling matches

**Issue: Form not submitting**
- Check browser console for JavaScript errors (F12)
- Verify input validation logic
- Check if modal is open/visible

**Issue: Blank page after booking**
- Check if app.js is loaded: Open DevTools → Sources tab
- Check console for errors
- Verify API response format

### 5. Testing with Postman (Advanced)

1. Download Postman: https://www.postman.com/downloads/
2. Create new request
3. Set method to POST
4. URL: http://localhost:5000/api/book
5. Headers: Content-Type: application/json
6. Body (raw):
```json
{
    "movie_id": 1,
    "show": "10:00 AM",
    "seats": 2,
    "name": "Test User",
    "phone": "9876543210"
}
```
7. Send request and check response

---

## Summary

### Key Points:
✅ Python backend serves API endpoints
✅ JavaScript fetches data from API
✅ Frontend displays data in HTML
✅ CSS makes it look beautiful
✅ CORS allows frontend-backend communication
✅ JSON format for data transfer

### Flow:
1. User opens HTML page
2. JavaScript runs automatically
3. Fetches movies from backend
4. Displays movies in browser
5. User fills form and submits
6. JavaScript sends data to backend
7. Backend validates and saves booking
8. Backend returns confirmation
9. JavaScript updates page with confirmation

### Next Steps:
- Add more features (search, filters)
- Implement user authentication
- Add database persistence
- Deploy to cloud
- Add payment integration

---

**Happy Building! 🎬**
