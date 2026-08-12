# CINEBOOK - Cinema Ticket Booking System
## Complete Development Guide: From Setup to Deployment

---

## Table of Contents
1. [Project Overview](#project-overview)
2. [Project Structure](#project-structure)
3. [Setting Up Development Environment](#setting-up-development-environment)
4. [Understanding the Python Codebase](#understanding-the-python-codebase)
5. [Building the Management System](#building-the-management-system)
6. [Version Control & GitHub](#version-control--github)
7. [Deployment](#deployment)
8. [Troubleshooting](#troubleshooting)

---

## Project Overview

**CINEBOOK** is a Python-based Cinema Ticket Booking Management System that allows users to:
- View available movies with details (language, price, show timings)
- Book movie tickets
- Manage customer information
- View booking history
- Calculate and manage pricing

### Key Features:
- Movie inventory management
- Booking confirmation with unique IDs
- Customer data storage
- Show timings management
- Seat limit validation (Max 10 seats)
- Dynamic pricing calculation

---

## Project Structure

### Recommended Folder Organization:

```
CINEBOOK-Project/
│
├── main.py                          # Main application file (current)
├── requirements.txt                 # Python dependencies
├── README.md                        # Project documentation
├── .gitignore                       # Git ignore file
│
├── src/                             # Source code folder
│   ├── __init__.py
│   ├── models.py                    # Data models/classes
│   ├── database.py                  # Database operations
│   ├── validators.py                # Input validation
│   └── utils.py                     # Utility functions
│
├── tests/                           # Unit tests
│   ├── __init__.py
│   ├── test_booking.py
│   └── test_movies.py
│
├── static/                          # Frontend files (HTML, CSS, JS)
│   ├── index.html
│   ├── css/
│   │   └── style.css
│   └── js/
│       └── script.js
│
├── templates/                       # HTML templates (if using Flask)
│   ├── base.html
│   ├── movies.html
│   └── bookings.html
│
└── docs/                            # Documentation
    ├── API.md
    ├── SETUP.md
    └── DEPLOYMENT.md
```

---

## Setting Up Development Environment

### Step 1: Install Python (Windows)

1. **Download Python:**
   - Visit: https://www.python.org/downloads/
   - Download Python 3.9+ (Latest version recommended)

2. **Install Python:**
   - Run installer
   - ✅ CHECK: "Add Python to PATH"
   - Click "Install Now"

3. **Verify Installation:**
   ```bash
   python --version
   pip --version
   ```

### Step 2: Create Project Folder

```bash
# Navigate to desired location
cd C:\Users\PC\OneDrive\Documents\FOE_6\

# Create project folder
mkdir CINEBOOK-Project
cd CINEBOOK-Project
```

### Step 3: Create Virtual Environment

A virtual environment isolates your project dependencies.

```bash
# Create virtual environment
python -m venv venv

# Activate virtual environment
# On Windows:
venv\Scripts\activate

# You should see (venv) in terminal
```

### Step 4: Create requirements.txt

```bash
pip install flask
pip install flask-cors
pip freeze > requirements.txt
```

**Contents of requirements.txt:**
```
Flask==2.3.0
Flask-CORS==4.0.0
Werkzeug==2.3.0
```

### Step 5: Open in VS Code

```bash
# Open current folder in VS Code
code .
```

---

## Understanding the Python Codebase

### Current Code Structure Analysis

#### 1. **Data Storage (In-Memory)**
```python
movies = {  # Dictionary structure
    1: {
        "name": "Odyssey",
        "language": "English",
        "price": 295,
        "shows": ["10:00 AM", "2:00 PM", "6:00 PM"]
    }
}

bookings = []  # List to store booking records
```

**Understanding:**
- `movies`: Dictionary with movie ID as key
- Each movie has: name, language, price, shows (list)
- `bookings`: List to store customer booking data

#### 2. **Main Functions**

**A. display_movies()**
- Shows all available movies
- Displays: Movie ID, Name, Language, Price, Show times
- No parameters needed
- Prints formatted output

**B. book_movie()**
- Handles complete booking process
- Steps:
  1. Display available movies
  2. User selects movie ID
  3. User selects show time
  4. User enters number of seats
  5. Collects customer details (name, phone)
  6. Calculates total price
  7. Creates booking with unique ID
  8. Stores in bookings list
  9. Displays confirmation

**C. view_booking()**
- Shows all existing bookings
- Displays: Booking ID, Customer name, Phone, Movie name

#### 3. **Validation Logic**
- Movie ID validation (must exist)
- Show selection validation (1 to available shows)
- Seat validation (1-10 seats)
- Name and phone collection

---

## Building the Management System

### Phase 1: Refactor Python Code (Recommended)

#### Step 1: Create src/models.py

```python
# src/models.py
class Movie:
    def __init__(self, movie_id, name, language, price, shows):
        self.id = movie_id
        self.name = name
        self.language = language
        self.price = price
        self.shows = shows

class Booking:
    booking_counter = 1001
    
    def __init__(self, name, phone, movie, show, seats, price):
        self.booking_id = f"CB{Booking.booking_counter}"
        Booking.booking_counter += 1
        self.name = name
        self.phone = phone
        self.movie = movie
        self.show = show
        self.seats = seats
        self.total_price = price * seats

class BookingManager:
    def __init__(self):
        self.movies = {}
        self.bookings = []
    
    def add_movie(self, movie):
        self.movies[movie.id] = movie
    
    def add_booking(self, booking):
        self.bookings.append(booking)
    
    def get_all_movies(self):
        return self.movies
    
    def get_all_bookings(self):
        return self.bookings
```

#### Step 2: Create src/validators.py

```python
# src/validators.py
class Validator:
    @staticmethod
    def validate_movie_id(movie_id, valid_ids):
        return movie_id in valid_ids
    
    @staticmethod
    def validate_show_selection(show_index, total_shows):
        return 1 <= show_index <= total_shows
    
    @staticmethod
    def validate_seats(seats):
        return 1 <= seats <= 10
    
    @staticmethod
    def validate_customer_name(name):
        return len(name.strip()) > 0
    
    @staticmethod
    def validate_phone(phone):
        return phone.isdigit() and len(phone) >= 10
```

#### Step 3: Refactor main.py

```python
# main.py
from src.models import Movie, Booking, BookingManager
from src.validators import Validator

# Initialize manager
manager = BookingManager()

# Add sample movies
movies_data = [
    (1, "Odyssey", "English", 295, ["10:00 AM", "2:00 PM", "6:00 PM"]),
    (2, "The Conjuring", "English", 215, ["11:00 AM", "3:00 PM", "7:00 PM"]),
    (3, "The Conjuring 2", "English", 233, ["9:00 AM", "5:00 PM", "11:00 PM"])
]

for m_id, name, lang, price, shows in movies_data:
    movie = Movie(m_id, name, lang, price, shows)
    manager.add_movie(movie)

# Rest of your code...
```

### Phase 2: Add Database Support (Optional but Recommended)

#### Step 1: Install SQLite (Built-in with Python)

```bash
# No installation needed! SQLite comes with Python
# Just create src/database.py
```

#### Step 2: Create src/database.py

```python
# src/database.py
import sqlite3
from datetime import datetime

class Database:
    def __init__(self, db_name="cinebook.db"):
        self.conn = sqlite3.connect(db_name)
        self.cursor = self.conn.cursor()
        self.create_tables()
    
    def create_tables(self):
        # Movies table
        self.cursor.execute("""
            CREATE TABLE IF NOT EXISTS movies (
                id INTEGER PRIMARY KEY,
                name TEXT NOT NULL,
                language TEXT,
                price REAL,
                shows TEXT
            )
        """)
        
        # Bookings table
        self.cursor.execute("""
            CREATE TABLE IF NOT EXISTS bookings (
                booking_id TEXT PRIMARY KEY,
                name TEXT NOT NULL,
                phone TEXT,
                movie TEXT,
                show TEXT,
                seats INTEGER,
                total_price REAL,
                booking_date TIMESTAMP
            )
        """)
        
        self.conn.commit()
    
    def insert_booking(self, booking):
        self.cursor.execute("""
            INSERT INTO bookings VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """, (
            booking.booking_id,
            booking.name,
            booking.phone,
            booking.movie,
            booking.show,
            booking.seats,
            booking.total_price,
            datetime.now()
        ))
        self.conn.commit()
    
    def get_all_bookings(self):
        self.cursor.execute("SELECT * FROM bookings")
        return self.cursor.fetchall()
    
    def close(self):
        self.conn.close()
```

### Phase 3: Create Web API (Flask)

#### Step 1: Create app.py

```python
# app.py
from flask import Flask, jsonify, request
from flask_cors import CORS
from src.models import Movie, Booking, BookingManager
from src.validators import Validator

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

# API Routes

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
    if not Validator.validate_movie_id(data['movie_id'], manager.movies.keys()):
        return jsonify({'success': False, 'error': 'Invalid movie ID'}), 400
    
    if not Validator.validate_seats(data['seats']):
        return jsonify({'success': False, 'error': 'Invalid seat count (1-10)'}), 400
    
    if not Validator.validate_customer_name(data['name']):
        return jsonify({'success': False, 'error': 'Invalid name'}), 400
    
    if not Validator.validate_phone(data['phone']):
        return jsonify({'success': False, 'error': 'Invalid phone number'}), 400
    
    # Create booking
    movie = manager.movies[data['movie_id']]
    booking = Booking(
        data['name'],
        data['phone'],
        movie.name,
        data['show'],
        data['seats'],
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

if __name__ == '__main__':
    app.run(debug=True, port=5000)
```

#### Step 2: Run Flask Application

```bash
python app.py

# Server runs at: http://localhost:5000
```

---

## Version Control & GitHub

### Step 1: Initialize Git

```bash
# Initialize git repository
git init

# Add all files
git add .

# Create initial commit
git commit -m "Initial commit: CINEBOOK Management System"
```

### Step 2: Create .gitignore

Create `.gitignore` file:
```
# Python
__pycache__/
*.py[cod]
*$py.class
*.so
.Python
venv/
env/

# IDE
.vscode/
.idea/
*.swp

# Database
*.db
*.sqlite

# Environment
.env

# Flask
instance/
.webassets-cache

# Testing
.pytest_cache/
.coverage
```

### Step 3: Push to GitHub

```bash
# Create repository on GitHub (github.com)
# Get the repository URL

# Add remote
git remote add origin https://github.com/yourusername/CINEBOOK.git

# Push to GitHub
git branch -M main
git push -u origin main

# Subsequent pushes:
git add .
git commit -m "Your message here"
git push
```

---

## Deployment

### Option 1: Deploy on Heroku (Recommended for beginners)

#### Step 1: Create Heroku Account
- Visit: https://www.heroku.com
- Sign up (free tier available)

#### Step 2: Install Heroku CLI
```bash
# Download from: https://devcenter.heroku.com/articles/heroku-cli
# Then verify:
heroku --version
```

#### Step 3: Create Procfile

Create file named `Procfile` (no extension):
```
web: gunicorn app:app
```

#### Step 4: Update requirements.txt

```bash
pip install gunicorn
pip freeze > requirements.txt
```

#### Step 5: Deploy

```bash
# Login to Heroku
heroku login

# Create Heroku app
heroku create your-app-name

# Deploy
git push heroku main

# Open app
heroku open

# View logs
heroku logs --tail
```

### Option 2: Deploy on PythonAnywhere

#### Step 1: Create Account
- Visit: https://www.pythonanywhere.com

#### Step 2: Upload Files
- Use web interface to upload project files
- Install packages via console

#### Step 3: Configure Web App
- Set Python version to 3.9+
- Configure WSGI settings
- Point to Flask app

---

## Troubleshooting

### Issue 1: Python Not Found
**Solution:**
```bash
# Check installation
python --version

# If not found, reinstall Python and add to PATH
```

### Issue 2: venv Activation Fails
**Solution:**
```bash
# Delete venv folder
rmdir venv /s

# Recreate
python -m venv venv
venv\Scripts\activate
```

### Issue 3: Module Not Found Error
**Solution:**
```bash
# Ensure virtual environment is active
# Install missing module
pip install module_name

# Update requirements.txt
pip freeze > requirements.txt
```

### Issue 4: Port 5000 Already in Use
**Solution:**
```bash
# Use different port
python app.py --port 5001

# Or kill the process using port 5000
# On Windows PowerShell:
Get-Process -Id (Get-NetTCPConnection -LocalPort 5000).OwningProcess | Stop-Process -Force
```

### Issue 5: CORS Errors in Frontend
**Solution:**
```python
# Ensure Flask-CORS is installed
pip install flask-cors

# In app.py, add:
from flask_cors import CORS
CORS(app)
```

---

## Best Practices

✅ **DO:**
- Use virtual environments
- Keep code modular and organized
- Write comments for complex logic
- Test before pushing to GitHub
- Use descriptive commit messages
- Update requirements.txt after installing packages
- Use meaningful variable names

❌ **DON'T:**
- Hardcode sensitive information (use .env)
- Commit __pycache__ folders
- Skip input validation
- Use global variables excessively
- Deploy without testing

---

## Next Steps

1. ✅ Refactor code into modules (src/ folder)
2. ✅ Add unit tests (tests/ folder)
3. ✅ Connect with frontend (see FRONTEND_INTEGRATION.md)
4. ✅ Set up database (SQLite)
5. ✅ Deploy to cloud (Heroku/PythonAnywhere)
6. ✅ Add authentication
7. ✅ Create API documentation

---

**Happy Coding! 🎬🎥**
