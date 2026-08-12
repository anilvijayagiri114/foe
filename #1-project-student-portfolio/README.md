# Student Portfolio — Complete Beginner Project

A clean, simple and reusable student portfolio website built with:

- HTML
- CSS
- JavaScript
- Git
- GitHub
- GitHub Pages

This project is designed as a **reference project for students**.

You can download it, understand the structure, replace the sample information with your own information and publish your portfolio online.

No React, Node.js, npm, Vite or backend is required.

---

# 1. What will you build?

You will build a personal student portfolio containing:

```text
Home
  ↓
About Me
  ↓
Education
  ↓
Skills
  ↓
Projects
  ↓
Certifications
  ↓
Achievements
  ↓
Contact
```

The finished website can be used when applying for:

- Internships
- College projects
- Hackathons
- Student developer programs
- Entry-level jobs
- Freelance opportunities

---

# 2. Technologies used

## HTML

HTML creates the structure of the website.

Examples:

```html
<h1>My Name</h1>
<p>I am a student.</p>
```

HTML is used for:

- Headings
- Paragraphs
- Buttons/links
- Sections
- Project cards
- Images
- Navigation

---

## CSS

CSS controls how the website looks.

It is used for:

- Colors
- Fonts
- Spacing
- Cards
- Buttons
- Layout
- Responsive design
- Mobile navigation styling

Example:

```css
.button {
  padding: 12px 20px;
  background: blue;
  color: white;
}
```

---

## JavaScript

JavaScript adds small interactive features.

This project uses JavaScript for:

1. Mobile navigation
2. Scroll reveal animation
3. Automatic copyright year

The JavaScript is intentionally simple so beginners can understand it.

---

# 3. Project structure

```text
student-portfolio/
│
├── index.html
│
├── style.css
│
├── script.js
│
├── README.md
│
└── assets/
    ├── profile-placeholder.svg
    └── resume.pdf
```

---

# 4. What each file does

## index.html

This is the main webpage.

It contains:

```text
Navigation
Hero
About
Education
Skills
Projects
Certifications
Achievements
Contact
Footer
```

---

## style.css

This controls the visual design.

It contains:

```text
Reset
Variables
Header
Hero
About
Education
Skills
Projects
Certifications
Achievements
Contact
Footer
Responsive design
Animations
```

---

## script.js

This provides basic interaction.

```text
Mobile menu
Scroll animation
Current year
```

---

## assets/

Put files that belong to your portfolio here.

Example:

```text
assets/
├── profile.jpg
└── resume.pdf
```

---

# 5. First step — change the sample student

Open:

```text
index.html
```

Search for:

```text
Alex Johnson
```

Replace it with your name.

Example:

```html
<h1>
  Hi, I'm <span>Rahul Kumar</span>
</h1>
```

---

# 6. Change your course

Find:

```text
Computer Science Student & Aspiring Software Developer
```

Change it to your actual role.

Examples:

```text
B.Tech Computer Science Student
```

```text
Electronics Engineering Student
```

```text
BCA Student & Web Developer
```

```text
Mechanical Engineering Student
```

---

# 7. Change the About section

Find:

```html
<section class="section" id="about">
```

Update the paragraphs.

A good student introduction should answer:

```text
Who am I?
What am I studying?
What technologies do I like?
What am I currently learning?
What kind of opportunity am I looking for?
```

Example:

```text
I am a third-year Information Technology student interested
in backend development and cloud computing. I enjoy building
small applications and learning through practical projects.
```

---

# 8. Change education

Find:

```html
<section class="section section-alt" id="education">
```

Update:

- Degree
- College
- Start year
- End year
- CGPA
- Percentage
- Relevant subjects

Example:

```text
B.Tech — Information Technology
ABC College of Engineering
2023 — 2027
CGPA: 8.4 / 10
```

---

# 9. Change skills

The sample portfolio contains:

```text
Programming
Web Development
Database
Tools
```

You can add or remove skills.

For example:

```html
<div class="tags">
  <span>Python</span>
  <span>Java</span>
  <span>SQL</span>
</div>
```

Do not add technologies you have never used.

A smaller list of genuine skills is better than a huge list of technologies you cannot explain in an interview.

---

# 10. Projects — the most important section

For every project, try to explain:

```text
1. What problem did I solve?
2. What did I build?
3. Which technologies did I use?
4. What features did it have?
5. What did I learn?
6. Where is the source code?
7. Is there a live demo?
```

Example:

```text
Student Task Manager

Problem:
Students need a simple way to track daily tasks.

Solution:
Built a browser-based task manager.

Technology:
HTML, CSS and JavaScript.

Features:
- Add tasks
- Delete tasks
- Mark tasks complete
- Filter tasks

GitHub:
Repository URL

Live Demo:
GitHub Pages URL
```

---

# 11. How to add a project

Copy one existing project:

```html
<article class="project-card reveal">
```

Then change:

```text
Project number
Project type
Project name
Description
Technologies
GitHub URL
Live URL
```

Example:

```html
<article class="project-card reveal">

  <div class="project-number">05</div>

  <p class="project-type">PYTHON</p>

  <h3>Expense Tracker</h3>

  <p>
    A Python application that allows users to record expenses
    and calculate monthly spending.
  </p>

  <div class="tags">
    <span>Python</span>
    <span>SQLite</span>
  </div>

  <div class="project-links">
    <a href="https://github.com/YOUR_USERNAME/expense-tracker">
      GitHub ↗
    </a>
  </div>

</article>
```

---

# 12. Add your GitHub repository

Change:

```html
href="https://github.com/"
```

to:

```html
href="https://github.com/YOUR_USERNAME/YOUR_REPOSITORY"
```

Example:

```html
<a
  href="https://github.com/rahulkumar/student-task-manager"
  target="_blank"
  rel="noopener noreferrer"
>
  GitHub ↗
</a>
```

---

# 13. Add a live project

If your project is deployed:

```html
<a href="https://your-project-url.com">
  Live Demo ↗
</a>
```

If it is not deployed, remove the Live Demo link.

Do not leave fake links in your final portfolio.

---

# 14. Add your profile picture

The project currently contains:

```text
assets/profile-placeholder.svg
```

Replace it with:

```text
assets/profile.jpg
```

Then change this in `index.html`:

```html
<img
  src="assets/profile-placeholder.svg"
  alt="Profile placeholder"
>
```

to:

```html
<img
  src="assets/profile.jpg"
  alt="Your Name"
>
```

Recommended:

- JPG
- PNG
- WebP
- Reasonable file size
- Clear professional photograph

---

# 15. Add your resume

Put your resume here:

```text
assets/resume.pdf
```

The existing button:

```html
<a href="assets/resume.pdf" download>
```

will then download your resume.

Make sure the filename is exactly:

```text
resume.pdf
```

---

# 16. Add LinkedIn

Find:

```html
https://www.linkedin.com/
```

Replace it with your profile.

Example:

```html
https://www.linkedin.com/in/your-name/
```

---

# 17. Add your email

Find:

```html
mailto:alex@example.com
```

Replace it:

```html
mailto:yourname@gmail.com
```

---

# 18. Certifications

Add only genuine certifications.

Example:

```text
Python Programming — Coursera
AWS Cloud Practitioner — AWS
Web Development — freeCodeCamp
```

If a certificate has a verification URL, put it into:

```html
<a href="YOUR_CERTIFICATE_URL">
  View Certificate ↗
</a>
```

---

# 19. Achievements

Good examples:

```text
Hackathon finalist
Coding competition
Technical club
Paper presentation
Workshop speaker
Open-source contribution
Academic achievement
Volunteer work
Sports achievement
Leadership position
```

Only include things you actually achieved.

---

# 20. Local testing

You can test this project without installing anything.

Open:

```text
index.html
```

in your browser.

You should see the portfolio.

Test:

- Navigation
- Buttons
- Projects
- Resume
- GitHub
- LinkedIn
- Mobile layout

---

# 21. Recommended — use VS Code

Install VS Code:

https://code.visualstudio.com/

Open:

```text
student-portfolio
```

Then open:

```text
index.html
```

You can also install the **Live Server** extension.

Then right-click:

```text
index.html
```

and select:

```text
Open with Live Server
```

Your browser will open the local website.

---

# 22. Install Git

Download Git:

https://git-scm.com/downloads

Check installation:

```bash
git --version
```

You should see something similar to:

```text
git version 2.x.x
```

---

# 23. Create your GitHub repository

Go to:

https://github.com/new

Create a repository.

Recommended name:

```text
student-portfolio
```

or:

```text
your-name-portfolio
```

Example:

```text
rahul-portfolio
```

For a beginner portfolio, making the repository public is useful because recruiters and interviewers can inspect the source code.

---

# 24. Open the terminal in your project

Navigate to your project folder.

Windows example:

```bash
cd "C:\Users\YourName\Desktop\student-portfolio"
```

Check the files:

```bash
dir
```

You should see:

```text
index.html
style.css
script.js
README.md
assets
```

---

# 25. Initialize Git

Run:

```bash
git init
```

This creates a local Git repository.

Then:

```bash
git status
```

---

# 26. Add the files

Run:

```bash
git add .
```

Then:

```bash
git status
```

Your files should now be ready for commit.

---

# 27. Create your first commit

Run:

```bash
git commit -m "Create student portfolio"
```

A commit is a saved version of your project.

---

# 28. Connect the project to GitHub

Copy your repository URL from GitHub.

Example:

```text
https://github.com/rahulkumar/student-portfolio.git
```

Run:

```bash
git remote add origin https://github.com/YOUR_USERNAME/student-portfolio.git
```

Check:

```bash
git remote -v
```

---

# 29. Push to GitHub

Run:

```bash
git branch -M main
```

Then:

```bash
git push -u origin main
```

Refresh your GitHub repository.

You should now see:

```text
index.html
style.css
script.js
README.md
assets/
```

---

# 30. Deploy with GitHub Pages

GitHub Pages can publish static HTML, CSS and JavaScript files directly from a GitHub repository. citeturn0search0turn0search10

Go to your repository:

```text
GitHub
→ Your Repository
→ Settings
→ Pages
```

Under:

```text
Build and deployment
```

select:

```text
Source:
Deploy from a branch
```

Then:

```text
Branch:
main

Folder:
/
```

Click:

```text
Save
```

GitHub will deploy the website.

---

# 31. Your website URL

If your GitHub username is:

```text
rahulkumar
```

and your repository is:

```text
student-portfolio
```

your project website will normally be:

```text
https://rahulkumar.github.io/student-portfolio/
```

GitHub has two main types of Pages sites: user/organization sites and project sites. A project site is associated with a repository and normally uses the repository name in the URL. citeturn0search1

---

# 32. Alternative: personal GitHub website

If you want:

```text
https://YOUR_USERNAME.github.io/
```

instead of:

```text
https://YOUR_USERNAME.github.io/student-portfolio/
```

create a repository named exactly:

```text
YOUR_USERNAME.github.io
```

Example:

```text
rahulkumar.github.io
```

GitHub's official quickstart uses this naming pattern for a user site. citeturn0search7

---

# 33. How to update your portfolio later

Suppose you add a new project.

First test it.

Then:

```bash
git status
```

Add changes:

```bash
git add .
```

Commit:

```bash
git commit -m "Add new project"
```

Push:

```bash
git push
```

GitHub Pages will publish the updated version from the configured source. citeturn0search3

Your workflow becomes:

```text
Edit
  ↓
Test
  ↓
git add .
  ↓
git commit
  ↓
git push
  ↓
GitHub
  ↓
GitHub Pages
  ↓
Live website
```

---

# 34. Common Git commands

## Check changes

```bash
git status
```

## Add everything

```bash
git add .
```

## Commit

```bash
git commit -m "Your message"
```

## Push

```bash
git push
```

## Download latest repository changes

```bash
git pull
```

## See previous commits

```bash
git log --oneline
```

---

# 35. Common problems

## Problem: CSS is not loading

Check:

```html
<link rel="stylesheet" href="style.css">
```

Make sure:

```text
index.html
style.css
```

are in the same directory.

---

## Problem: JavaScript is not working

Check:

```html
<script src="script.js"></script>
```

Make sure:

```text
script.js
```

is in the root folder.

---

## Problem: Profile image does not appear

Check:

```text
assets/
└── profile.jpg
```

and:

```html
<img src="assets/profile.jpg">
```

The filename must match exactly.

---

## Problem: Resume does not download

Make sure:

```text
assets/
└── resume.pdf
```

exists.

And:

```html
<a href="assets/resume.pdf" download>
```

is correct.

---

## Problem: GitHub Pages shows 404

Check:

```text
Repository
→ Settings
→ Pages
```

Confirm:

```text
Branch = main
Folder = /
```

Also make sure:

```text
index.html
```

is in the root of the selected source.

GitHub provides official troubleshooting guidance for Pages sites and deployment problems. citeturn0search4

---

# 36. If your repository has another README

If you created the GitHub repository with a README before pushing your local project, you may have two different histories.

One normal solution is:

```bash
git pull --rebase origin main
```

Then:

```bash
git push
```

---

# 37. Do not put passwords or API keys here

This is extremely important.

A GitHub Pages website is public.

Never put:

```text
API_KEY
PASSWORD
SECRET
DATABASE_PASSWORD
AWS_SECRET
ACCESS_TOKEN
```

inside:

```text
index.html
style.css
script.js
```

Anyone can inspect frontend source code.

If you later build an application requiring secrets, use a backend/server-side application.

---

# 38. What should a good student portfolio contain?

Minimum:

```text
✓ Name
✓ Short introduction
✓ Education
✓ Skills
✓ 2–4 real projects
✓ GitHub
✓ LinkedIn
✓ Email
✓ Resume
```

Better:

```text
✓ Internships
✓ Certifications
✓ Achievements
✓ Hackathons
✓ Live project demos
✓ Technical articles
✓ Open-source contributions
```

---

# 39. What NOT to do

Avoid:

```text
❌ 50+ skills
❌ Fake projects
❌ Fake certifications
❌ Fake experience
❌ Broken GitHub links
❌ Broken Live Demo links
❌ Huge paragraphs
❌ Too many animations
❌ Unprofessional photos
❌ Exposing API keys
```

A simple portfolio containing **3 real projects** is better than a fancy portfolio containing 15 copied projects.

---

# 40. How to make your projects stronger

For every project, maintain a separate GitHub repository.

Example:

```text
GitHub
│
├── student-portfolio
├── task-manager
├── weather-app
├── expense-tracker
└── campus-event-portal
```

Each project repository should have its own README.

A project README can contain:

```text
Project Name

Problem Statement

Features

Technology Used

How to Run

Screenshots

Future Improvements

Author
```

Then your portfolio becomes the central place that connects all your projects.

---

# 41. Student portfolio checklist

Before publishing:

- [ ] Replace sample name
- [ ] Replace sample course
- [ ] Replace college
- [ ] Replace CGPA/percentage
- [ ] Replace location
- [ ] Replace skills
- [ ] Add real profile photo
- [ ] Add real resume
- [ ] Add 2–4 real projects
- [ ] Add GitHub repository links
- [ ] Add live demo links where available
- [ ] Add real certifications
- [ ] Add real achievements
- [ ] Add LinkedIn
- [ ] Add email
- [ ] Remove fake/sample information
- [ ] Test desktop view
- [ ] Test mobile view
- [ ] Test every link
- [ ] Test resume download
- [ ] Check browser console
- [ ] Push to GitHub
- [ ] Enable GitHub Pages
- [ ] Open the live URL
- [ ] Test the live URL again

---

# 42. Suggested learning order

If you are a complete beginner, don't try to understand the entire project in one day.

Learn in this order:

```text
HTML
 ↓
CSS
 ↓
Responsive CSS
 ↓
JavaScript basics
 ↓
Git
 ↓
GitHub
 ↓
GitHub Pages
```

Then learn:

```text
JavaScript DOM
 ↓
APIs
 ↓
React
 ↓
Backend
 ↓
Databases
 ↓
Cloud
```

---

# 43. Beginner challenge

After understanding this project, try these changes yourself:

### Level 1

Change:

```text
Name
Course
College
Skills
```

### Level 2

Add:

```text
One new project
One certification
One achievement
```

### Level 3

Add:

```text
Dark mode
```

### Level 4

Add:

```text
Project filtering
```

For example:

```text
All
Web
Python
Java
AI
```

### Level 5

Create your own portfolio design without copying this template.

That is when you know you have actually learned the basics.

---

# 44. Final project goal

The purpose of this project is not to give every student the exact same website.

The purpose is:

```text
Use this project
      ↓
Understand the code
      ↓
Replace sample information
      ↓
Add your own projects
      ↓
Customize the design
      ↓
Deploy it
      ↓
Use it as your personal portfolio
```

You should eventually make the portfolio your own.

---

# 45. Official GitHub Pages references

GitHub Pages overview:

https://docs.github.com/en/pages/getting-started-with-github-pages/what-is-github-pages

GitHub Pages getting started:

https://docs.github.com/en/pages/getting-started-with-github-pages

GitHub Pages quickstart:

https://docs.github.com/en/pages/quickstart

Creating a GitHub Pages site:

https://docs.github.com/en/pages/getting-started-with-github-pages/creating-a-github-pages-site

Configuring a publishing source:

https://docs.github.com/en/pages/getting-started-with-github-pages/configuring-a-publishing-source-for-your-github-pages-site

GitHub Pages is particularly suitable for this project because it can publish static HTML, CSS and JavaScript from a repository without requiring a separate hosting server. citeturn0search0turn0search6
