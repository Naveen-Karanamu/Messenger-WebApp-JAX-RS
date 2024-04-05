## Messenger REST API Documentation

This API provides functionalities for managing messages, comments, and profiles.

**Communication:**

- **Content-Type:** Application/JSON is used for both request and response bodies.
- **Error Responses:** Responses include an error message and status code in case of exceptions.

**1. Messages API**

**Endpoints:**

* **GET /messages**
    * Description: Retrieves a list of all messages.
    * Response: JSON array of `Message` objects.
* **GET /messages/{messageId}**
    * Description: Gets a specific message by its ID.
    * Path Parameter: `{messageId}` - The ID of the message to retrieve.
    * Response: JSON object representing the `Message`.
* **POST /messages/add**
    * Description: Creates a new message.
    * Request Body: JSON object representing the new `Message`.
    * Response: JSON object representing the created `Message` with a generated ID.
* **PUT /messages/update/{messageId}**
    * Description: Updates an existing message.
    * Path Parameter: `{messageId}` - The ID of the message to update.
    * Request Body: JSON object representing the updated `Message`.
    * Response: JSON object representing the updated `Message`.
* **DELETE /messages/delete/{messageId}**
    * Description: Deletes a message by its ID.
    * Path Parameter: `{messageId}` - The ID of the message to delete.
    * Response: Empty response on successful deletion (status code 204 No Content).

**2. Comments API**

These endpoints require the message ID as a path parameter (`{messageId}`) to specify the context of the comments.

* **GET /messages/{messageId}/comments/allComment**
    * Description: Retrieves all comments associated with a specific message.
    * Response: JSON array of `Comment` objects.
* **GET /messages/{messageId}/comments/{commentId}**
    * Description: Gets a specific comment by its ID within a message.
    * Path Parameter: `{commentId}` - The ID of the comment to retrieve.
    * Response: JSON object representing the `Comment`.
* **POST /messages/{messageId}/comments/addComment**
    * Description: Creates a new comment for a specific message.
    * Request Body: JSON object representing the new `Comment`.
    * Response: JSON object representing the created `Comment` with a generated ID.
* **PUT /messages/{messageId}/comments/{commentId}**
    * Description: Updates an existing comment within a message.
    * Path Parameter: `{commentId}` - The ID of the comment to update.
    * Request Body: JSON object representing the updated `Comment`.
    * Response: JSON object representing the updated `Comment`.
* **DELETE /messages/{messageId}/comments/{commentId}**
    * Description: Deletes a comment by its ID within a message.
    * Path Parameter: `{commentId}` - The ID of the comment to delete.
    * Response: Empty response on successful deletion (status code 204 No Content).

**3. Profiles API**

* **GET /profiles**
    * Description: Retrieves a list of all profiles.
    * Response: JSON array of `Profile` objects.
* **GET /profiles/{profileName}**
    * Description: Gets a specific profile by its name.
    * Path Parameter: `{profileName}` - The name of the profile to retrieve.
    * Response: JSON object representing the `Profile`.
* **POST /profiles/add**
    * Description: Creates a new profile.
    * Request Body: JSON object representing the new `Profile`.
    * Response: JSON object representing the created `Profile`.
* **PUT /profiles/update/{profileName}**
    * Description: Updates an existing profile.
    * Path Parameter: `{profileName}` - The name of the profile to update.
    * Request Body: JSON object representing the updated `Profile`.
    * Response: JSON object representing the updated `Profile`.
* **DELETE /profiles/delete/{profileName}**
    * Description: Deletes a profile by its name.
    * Path Parameter: `{profileName}` - The name of the profile to delete.
    * Response: JSON object representing the deleted `Profile` (optional, depending on implementation).

