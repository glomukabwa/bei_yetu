# bei_yetu

## About
This is a collaborative Android app built with Jetpack Compose and Room Database that helps users compare product prices across multiple retailers (Naivas, Carrefour, Quickmart, etc.).

## Features
- User authentication (login/signup/logout)
- Product listing with categories and subcategories
- Search funtionality for products
- Price comparison across different stores
- External store links (opens store websites directly)
- Comment section for user feedback
- Profile page and editting functionalities for this page

## Technologies used
- Kotlin (primary language)
- Jetpack Compose (UI)
- Room Database (local persistence)
- Navigation Component (multi‑screen flows)
- ViewModel + StateFlow (state management)
- Material Design Components (UI styling)

## Demo Pictures
- Sign up and Log in<br>
<img width="346" height="763" alt="image" src="https://github.com/user-attachments/assets/a412e364-9fe9-435e-bc03-1661fb177643" />
<img width="343" height="762" alt="image" src="https://github.com/user-attachments/assets/105e0714-8ca1-4870-b1ef-be3fb61f8de5" /><br><br>

- Home Page and Categories page<br>
<img width="346" height="764" alt="image" src="https://github.com/user-attachments/assets/fa8c25b3-79cd-4a69-a5f4-88cafbd1aaa1" />
<img width="347" height="761" alt="image" src="https://github.com/user-attachments/assets/20a67178-470b-4482-90cc-fcce52ed70b9" /><br><br>

- Product details page<br>
<img width="350" height="764" alt="image" src="https://github.com/user-attachments/assets/d7c0b43e-0322-4887-8be5-907514e7cf09" />
<img width="349" height="765" alt="image" src="https://github.com/user-attachments/assets/2d95feb8-6e32-453b-8887-d3a0fdb4f1c6" />
<img width="351" height="768" alt="image" src="https://github.com/user-attachments/assets/cbb0fa04-8278-42be-bfd1-06cddf35cdfa" />
<img width="346" height="764" alt="image" src="https://github.com/user-attachments/assets/8429baef-f32b-49ef-b648-c6af89312c2b" /><br><br>

- Account Page<br>
<img width="346" height="767" alt="image" src="https://github.com/user-attachments/assets/e6e160c9-fde8-4d19-b531-d287e660e653" />

## Future Improvements
🔹 **Authentication Enhancements**
- Add secure password storage and validation.
- Integrate social login (Google, Facebook, etc.).
- Support persistent sessions with auto‑login.<br>

🔹 **Product Features**
- Implement product search with filters (price range, category, store).
- Add product ratings and reviews from users.
- Support product favorites/wishlist.<br>

🔹 **Store Integration**
- Fetch live prices via APIs from Naivas, Carrefour, Quickmart (instead of static data).
- Add more retailers for broader comparison.
- Show store availability (in‑stock/out‑of‑stock).<br>

🔹 **UI/UX Improvements**
- Dark mode support.
- Better error handling and user feedback (snackbars, dialogs).
- Accessibility improvements (screen reader support, larger text options).<br>

🔹 **Navigation & Session**
- Smarter start destination (skip login if already authenticated).
- Add profile management (edit name, email, preferences).
- Improve logout flow with confirmation dialogs.<br>

🔹 **Database & Performance**
- Sync product data with a remote backend (Firebase, Supabase, or custom API).
- Cache store links and product images for offline viewing.
- Optimize queries with Room relations and indexing.<br>

🔹 **Community Features**
- Expand comments into threaded discussions.
- Add likes/upvotes for comments.
- Enable reporting inappropriate content.<br>

🔹 **Notifications**
- Push notifications for price drops.
- Alerts when a product is back in stock.
- Personalized recommendations.<br>

🔹 **Testing & Deployment**
- Add unit tests and UI tests.
- Continuous integration (CI/CD) pipeline.
- Publish to Google Play Store.

---

🔹 **Original Repository**
Forked from https://github.com/LMaithya/bei_yetu
