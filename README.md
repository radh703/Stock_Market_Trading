# Stock Market Trading Platform

This repository contains a multi-service stock market trading platform with:
- A **Spring Boot backend** for portfolio and investment management APIs.
- An **Angular frontend** (`backOffice`) for dashboard, portfolio operations, and UI workflows.
- **Python FastAPI ML services** for clustering and prediction features.

## Repository Structure

- `PROJECT-BACKEND`  
  Java Spring Boot application (REST API, persistence, business services).

- `PROJECT-FOANTEND/backOffice`  
  Angular application for the admin/back-office interface.

- `PIDEV-Machine-Learning`  
  FastAPI-based machine learning endpoints (DBSCAN + prediction APIs).

## Core Features

- Portfolio CRUD operations.
- Portfolio investment and order management.
- Portfolio clustering integration via ML API.
- Prediction endpoint integration for portfolio volume analytics.
- Admin-style dashboard UI with modular Angular pages.

## Tech Stack

- **Backend:** Java 21, Spring Boot, Spring Data JPA, Maven
- **Frontend:** Angular 17, TypeScript, Angular Material
- **ML APIs:** Python, FastAPI, NumPy, scikit-learn / model loaders
- **Database:** MySQL (or H2 default fallback for local startup)

## Quick Start

### 1) Backend (`PROJECT-BACKEND`)

Requirements:
- Java 21
- `JAVA_HOME` configured

Run:
- `.\mvnw.cmd clean spring-boot:run`

Default API base path:
- `http://localhost:8082/PIDEV`

### 2) Frontend (`PROJECT-FOANTEND/backOffice`)

Requirements:
- Node.js 20+

Run:
- `npm install`
- `npm start`

Frontend URL:
- `http://localhost:4200`

### 3) ML Services (`PIDEV-Machine-Learning`)

Run each API with FastAPI/uvicorn, for example:
- `uvicorn DBSCAN-API:app --reload --port 8000`
- `uvicorn Prediction-API:app --reload --port 8001`

Use environment variables to configure model paths when needed:
- `DBSCAN_MODEL_PATH`
- `PREDICTION_MODEL_PATH`
- `RANDOM_FOREST_MODEL_PATH`

## Configuration Notes

- Backend ML URLs are environment-configurable:
  - `ML_API_URL` (default: `http://127.0.0.1:8000/apply_dbscan`)
  - `PREDICTION_API_URL` (default: `http://127.0.0.1:8000/Prediction`)
- Backend datasource and mail credentials should be set via environment variables for secure deployments.

## Status

This project is organized as a monorepo and currently includes backend, frontend, and ML components ready for local development and iterative enhancement.