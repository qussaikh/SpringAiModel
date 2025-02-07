# Qussai AI Model Project

The `Qussai AI Model` is a Spring Boot REST API controller that provides endpoints for interacting with AI-based services. It allows users to:

- Get text responses from a chatbot.
- Generate AI-based images.
- Create recipes based on provided ingredients and preferences.
- Generate AI-search or suggest matching job listings.
- Generate AI-search or suggest Travel Itinerary Planner.


## Prerequisites

Before running the application, ensure you have the following:

- Java 17 or higher.
- Maven or Gradle for dependency management.
- A configured and running backend service for the `OpenAI` and `Spring Web` dependencies.

### Open API Key
  - Step 1: Sign in to OpenAI
    - Go to the OpenAI website.
    - Click on the Sign In button (top-right corner).
    - Enter your credentials (email and password) or log in using a third-party service (e.g., Google, Microsoft).
  - Step 2: Access the API Section
    - After logging in, navigate to the API section:
          - OpenAI API Platform.
    - If you don't already have an account for the API platform, sign up for one.
    - Step 3: Navigate to the API Keys Section
         - Once logged in to the API dashboard, click on your profile icon (top-right corner).
         - Select API Keys from the dropdown menu.
  - Step 4: Generate a New API Key
      - In the API Keys section, click the Create New Secret Key button.
      - Copy the generated API key. You won’t be able to view it again once you close this page.
      - Save the key securely in a password manager or a secure file.
  - Step 5: Configure the API Key
    - put the API key in  application.properties file.

## Endpoints

### 1. **Ask AI**

Get a response from the chatbot.

- **URL**: `/ask-ai`
- **Method**: `GET`
- **Query Parameters**:
    - `prompt` (String, required): The input prompt for the chatbot.
- **Response**: A string response generated by the chatbot.

#### Example Request:
```http
GET http://localhost:8080/ask-ai?prompt=Hello
```

#### Example Response:
```
"Hi there! How can I assist you today?"
```
![askAi.png](Images%2FaskAi.png)
---

### 2. **Ask AI with Options**

Get multiple response options from the chatbot.

- **URL**: `/ask-ai-options`
- **Method**: `GET`
- **Query Parameters**:
    - `prompt` (String, required): The input prompt for the chatbot.
- **Response**: A string containing multiple response options.

#### Example Request:
```http
GET http://localhost:8080/ask-ai-options?prompt=Tell me a joke
```

#### Example Response:
```
"Option 1: Why did the scarecrow win an award? He was outstanding in his field!"
```

---

### 3. **Generate Images**

Generate AI-based images from a given prompt.

- **URL**: `/generate-image`
- **Method**: `GET`
- **Query Parameters**:
    - `prompt` (String, required): The input description for the image generation.
    - `quality` (String, optional, default: `hd`): The quality of the generated images.
    - `n` (int, optional, default: `2`): The number of images to generate.
    - `width` (int, optional, default: `1024`): The width of the generated images in pixels.
    - `height` (int, optional, default: `1024`): The height of the generated images in pixels.
- **Response**: A list of URLs for the generated images.

#### Example Request:
```http
GET http://localhost:8080/generate-image?prompt=Sunset&quality=hd&n=2&width=800&height=600
```

#### Example Response:
```json
[
  "https://example.com/image1.png",
  "https://example.com/image2.png"
]
```

---

### 4. **Recipe Creator**

Create a recipe based on given ingredients and preferences.

- **URL**: `/recipe-creator`
- **Method**: `GET`
- **Query Parameters**:
    - `ingredients` (String, required): A comma-separated list of ingredients.
    - `cuisine` (String, optional, default: `any`): The preferred cuisine type.
    - `dietaryRestriction` (String, optional, default: empty): Any dietary restrictions to consider.
- **Response**: A string containing the recipe details.

#### Example Request:
```http
GET http://localhost:8080/recipe-creator?ingredients=chicken,tomato,onion&cuisine=Italian&dietaryRestriction=gluten-free
```

#### Example Response:
```
"Title: Gluten-Free Italian Chicken with Tomato and Onion

Ingredients:
- 4 boneless, skinless chicken breasts
- 2 large tomatoes, diced
- 1 large onion, sliced
- 2 cloves of garlic, minced
- 1 teaspoon dried oregano
- 1 teaspoon dried basil
- Salt and pepper, to taste
- 2 tablespoons olive oil

Instructions:
1. In a large skillet, heat the olive oil over medium heat. Add the sliced onion and minced garlic, and sauté until the onion is translucent.
2. Season the chicken breasts with salt, pepper, dried oregano, and dried basil. Add the seasoned chicken breasts to the skillet and cook for about 5-7 minutes on each side, or until they are cooked through.
3. Once the chicken is cooked, remove it from the skillet and set it aside on a plate.
4. In the same skillet, add the diced tomatoes and cook for about 5 minutes, until they start to soften.
5. Return the chicken to the skillet with the tomatoes and onions. Cover the skillet and let it simmer for an additional 5 minutes to allow the flavors to blend.
6. Serve the Gluten-Free Italian Chicken with Tomato and Onion hot, garnished with fresh basil if desired. Enjoy!"
```
![recipeGen.png](Images%2FrecipeGen.png)
---

### 4. **Job seeking**

Generate AI-search or suggest matching job listings.

- **URL**: `/searching-job`
- **Method**: `GET`
- **Query Parameters**:
    - `jobTitle` (String, required).
    - `field` (String, optional): The preferred cuisine type.
    - `location` (String, optional, default: empty).
    - `employmentType` (String, optional, default: empty).
- **Response**: A list of job suggest .

#### Example Request:
```http
GET http://localhost:8080/searching-job?jobTitle=Software engineering&industry=backend&location=sweden, Skåne&employmentType=full time
```

#### Example Response:
- json
```
[
 "1. Job Title: Software Engineer
  Company: Sony Mobile Communications
  Location: Lund, Sweden
  Description: Sony Mobile Communications is looking for a software engineer to join their team in Lund, Sweden. The role involves developing and maintaining software applications for mobile devices.
  Application Link: [Sony Mobile Communications Careers](https://www.sonymobile.com/careers/)"
]
```
![jobSeeking.png](Images%2FjobSeeking.png)


### 5. **Travel plan**

Generate AI-search or suggest Travel Itinerary Planner.

- **URL**: `/travel-creator`
- **Method**: `GET`
- **Query Parameters**:
    - `destination` (String, required).
    - `cuisine` (String, optional): The preferred cuisine type.
    - `dietaryRestriction` (String, optional, default: empty).
- **Response**: A string containing the plan .

#### Example Request:
```http
GET http://localhost:8080/travel-creator?destination=Italy&interests=natur&days=6&budget=500 dollar
```

#### Example Response:
- json
```
'[
  Day 1: Arrival in Rome
  - Check into a budget-friendly hotel near the city center
  - Explore the historic center of Rome, including the Colosseum, Roman Forum, and Pantheon
  - Enjoy a traditional Italian dinner at a local trattoria

  Day 2: Rome
  - Visit the Vatican City and St. Peter's Basilica
  - Wander through the picturesque streets of Trastevere
  - Relax in Villa Borghese Park
  - Dinner at a cozy pizzeria

  Day 3: Tuscany
  - Take a day trip to the beautiful Tuscan countryside
  - Visit the charming town of Siena and its historic center
  - Enjoy a wine tasting at a local vineyard
  - Dinner at a traditional Tuscan restaurant

  Day 4: Florence
  - Check into a budget-friendly hotel in Florence
  - Explore the historic center of Florence, including the Duomo, Uffizi Gallery, and Ponte Vecchio
  - Visit the Boboli Gardens
  - Dinner at a local trattoria

  Day 5: Venice
  - Take a train to Venice
  - Explore the canals and bridges of Venice
  - Visit St. Mark's Basilica and the Doge's Palace
  - Enjoy a gondola ride through the city
  - Dinner at a waterside restaurant

  Day 6: Departure
  - Enjoy a leisurely breakfast in Venice
  - Take a vaporetto back to the train station
  - Depart for the airport for your return flight

  Total estimated cost:
  - Accommodations: $300
  - Transportation: $100
  - Activities: $50
  - Food: $50

  Total: $500

  This itinerary includes some of Italy's most iconic cities and attractions, while also allowing for some relaxation in the beautiful Tuscan countryside. Enjoy your trip to Italy!
]'
```
![travelGen.png](Images%2FtravelGen.png)

## How to Run

1. Clone the repository.
2. Build the application using Maven:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   java -jar target/genai-controller-1.0.jar
   ```
4. Access the API at `http://localhost:8080` (default port).

## reference 
https://platform.openai.com/docs/overview 

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Contact

For questions or support, contact [Qussai Khalil] at [qussaikhalil@icloud.com].
