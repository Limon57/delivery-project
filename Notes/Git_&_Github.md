# Git Commands Guide: Simple and Practical Notes

This guide walks you through real-world Git scenarios in a clear and simple way. Each section includes step-by-step instructions for tasks you’ll commonly face when using Git and GitHub.

---

## 🔧 Initial Setup

### 1. Configure Git with Your Name and Email

    git config --global user.name "Your Name"
    git config --global user.email "you@example.com"

### 2. Set the Default Branch Name to `main`

    git config --global init.defaultBranch main

### 3. Generate a GitHub Personal Access Token (PAT)

1. Go to [github.com](https://github.com)
2. Click your profile > Settings > Developer Settings > Personal Access Tokens
3. Generate a new token with `repo` permissions
4. Copy and save it — you won’t see it again

### 4. Authenticate Using the Token

When GitHub asks for your username/password:

- Use your **GitHub username**
- Use the **token** as your **password**

---

## 🚀 Starting a New Project

### 5. Initialize Git and Make First Commit

    cd your-project-folder
    git init
    echo "# Project Title" > README.md
    git add .
    git commit -m "Initial commit"

### 6. Add Remote and Push to GitHub

    git remote add origin https://github.com/yourusername/repo-name.git
    git push -u origin main

---

## 📤 Working with Commits and Pushes

### 7. Stage and Commit Changes

    git add filename
    # or all changes
    git add .
    git commit -m "Describe your changes"

### 8. Push to Remote Branch

    git push origin branch-name

### 9. Amend Last Commit Message

    git commit --amend -m "New message"

### 10. Undo Last Commit (Keep Changes)

    git reset --soft HEAD~1

---

## 🌿 Branching and Merging

### 11. Create and Switch to a New Branch

    git checkout -b feature-branch

### 12. Merge a Branch

    git checkout main
    git merge feature-branch

### 13. Delete Branch

    git branch -d feature-branch      # local
    git push origin --delete feature-branch  # remote

---

## 📥 Cloning and Pulling

### 14. Clone a Repository

    git clone https://github.com/user/repo.git

### 15. Pull Latest Changes

    git pull origin main

### 16. Fetch Without Merge

    git fetch origin

---

## 🛠️ Fixes and Recovery

### 17. Discard Local Changes in a File

    git checkout -- filename

### 18. Revert a Specific Commit

    git revert commit-hash

### 19. Reset to Previous Commit

    git reset --hard commit-hash

---

## 🔄 Working with Remotes

### 20. View Remotes

    git remote -v

### 21. Change Remote URL

    git remote set-url origin https://new-url.git

---

## 🔐 Private Repos and Authentication

### 22. Clone Private Repo with Token

    git clone https://<token>@github.com/username/private-repo.git

### 23. Cache GitHub Credentials (macOS/Linux)

    git config --global credential.helper cache

### 24. Use SSH Instead of HTTPS

1. Generate SSH key: `ssh-keygen -t ed25519`
2. Add the key to GitHub: Profile > Settings > SSH Keys
3. Use the SSH URL: `git@github.com:user/repo.git`

---

More sections can be added as needed. Each one is kept short and actionable so you can learn Git by doing it.
