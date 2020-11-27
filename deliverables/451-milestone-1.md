# Table of Contents

- Executive Summary
- [Deliverables](#deliverables)
- Work Done by Each Team Member
- Challenges Met During DevOps
- [Requirements](#requirements)
- Design documents
- API documentation
- Project plan
- User scenarios
- Code Structure Documentation
- Evaluation of Tools
- Evaluation of Managing
- [Assessment of the customer presentation](#assessment-of-the-customer-presentation)

# Deliverables

| Deliverable       | Status             |
|-------------------|--------------------|
| Requirements      | Complete           |
| Design Documents  | Complete           |
| API Documentation | Frequently updated |
| Project Plan      | Complete           |
| User scenarios    | Complete           |

**1- Requirements**
We've updated requirements according to the feedback from our new members and customer.

**2- Design Documents**
We haven't changed the design documents much because the customer and our team are good with it.

**3- API Documentation**
We're generating our API documentation automatically with swagger.

**4- Project Plan**
We've planned our tasks according to which milestone will include which requirement. It also includes due date and which person is responsible for that task.

**5- User scenarios** 
We've prepared two user scenarios for the milestone 1 presentation. These scenarios show the current functionality of the application.

# Requirements
## <a name="Glossary"></a> Glossary
* **Guest User** : The user who is only able to view the profiles that are public
* **Registered User** : The user who is registered to the system. **User** in the rest of glossary and requirements imply registered user.
* **Project Creator (Poster)** : The user who created a certain project.
* **Member** : Users that collaborate on a certain project.
* **Team** : A group of members.
* **Teammate** : The relation between two members.
* **Project Coordinators** : Members with the highest rank on a certain project.
* **Profile Page** : A page that stores information about a certain user.
* **Workspace** : The environment where members perform collaboration within a project.
* **Description** : The content describes the mechanism and the policy of the regarding the project
* **File** : The material that is a part of a certain project.
* **Search Engine** : A search tool which guests and users search content with keywords. Also contains advanced search with filters.
* **Semantic Search** : Semantic search denotes search with meaning, as distinguished from lexical search where the search engine looks for literal matches of the query words or variants of them, without understanding the overall meaning of the query.
* **Filter** : Detailed search criterion used in advanced search.
* **Event** : Journal submission activities, academic conferences, funded projects (e.g [Tubitak Projects](https://www.tubitak.gov.tr/tr/destekler/akademik/ulusal-destek-programlari) ).
* <mark> **Custom Event** : Events that are created by users. </mark>
* **Public** : The content that can be reached by anyone.
* **Private** : The content that can be reached only by the ones who are allowed.
* **Secure Enough Password** : Consists of at least six characters (and the more characters, the stronger the password) that are a combination of letters, numbers, and symbols. 

## <a name="1-Functional-Requirements"></a> 1. Functional Requirements
### <a name="1-1-User-Requirements"></a> 1.1. User Requirements
* #### 1.1.1.Guest User
  * <mark> **1.1.1.1.** Guests shall be able to register to the system. </mark>
  * <mark> **1.1.1.2.** Guests shall be able to search for  users, papers or projects. </mark>
  * **1.1.1.3.** Guests shall be able to see upcoming events, project titles and descriptions.
  * **1.1.1.4.** Guests shall be able to see public profile pages.
 
* #### 1.1.2. Registration and Sign in
  * **1.1.2.1.**  Users shall be able to register with a unique e-mail address, password, and full name.
  * **1.1.2.2.**  Users should be able to sign up with their Google accounts.
  * **1.1.2.3.**  Users shall be able to sign in using an email address and a password.
  * **1.1.2.4.**  Users shall be able to agree to Terms of Service and Privacy Policy while registering.
  * <mark> **1.1.2.5.**  Users shall be able to reset their forgotten password. </mark>
  * <mark> **1.1.2.6.**  A verification email shall sent to users after they sign up. </mark>

* #### 1.1.3. Project Creation and Gathering
  * **1.1.3.1.** Users shall be able to post their paper/project ideas/topics to collaborate with other users.
  * **1.1.3.2.** Posters shall be able to set the state of the post to "Seeking for Collaborators".
  * **1.1.3.3.** Users shall be able to specify requirements for collaboration on their posts.
  * **1.1.3.4.** Users shall be able to state their posts as public or private.
  * **1.1.3.5.** Users shall be able to send a collaboration request for a public post that is in the "Seeking for Collaborators" state.
  * **1.1.3.6.** Posters shall be able to send an invitation to another user to contribute to the post they created that is in the "Seeking for Collaborators" state.
  * **1.1.3.7.** Members shall be able to create a suggestion for inviting new users if the project is in "Seeking for Collaborators" state. 
* #### 1.1.4. Project Development
  * <mark> **1.1.4.1.** Users shall be able to share and monitor the submission document, codes, and any other file for the projects they are collaborating on. </mark>
  * <mark> **1.1.4.2.** Posters shall be able to add a custom event to the post with a title, description, date, link, location, submission deadline, and type. </mark>
  * **1.1.4.3.** Posters shall be able to add a milestone to the post with a description and date.
  * **1.1.4.4.** Posters shall be able to change the state of the post.
  * <mark> **1.1.4.5.** Posters shall be able to link an existing event to their project. </mark>
  * <mark> **1.1.4.6.** Members shall be able to determine and specify the requirement of the project that they are assigned to. </mark>
* #### 1.1.5. Profile System
  * **1.1.5.1.** Users shall be able to edit their profile information.
    * **1.1.5.1.1.** Users shall be able to change or add new photos for their profiles
    * **1.1.5.1.2.** Users shall be able to change their shown biological gender with one of the given next: 'male','female','do not want to share'
    * **1.1.5.1.3.** Users shall be able to decide whether to give the information of their age or not
    * **1.1.5.1.4.** Users shall be able to share their interests unrelated to their academic profession such as cosmology, astrology in their profiles.
  * **1.1.5.2.** Users shall be able to provide the information regarding expertise, bio, affiliation, recent publications, research area manually or by linking their Google Scholar or ResearchGate accounts
  * **1.1.5.3.** Users shall be able to add ratings and comments to their current or previous teammates.
  * **1.1.5.4.** Users shall be able to set their information either private or public to all users.
  * **1.1.5.5.** Users shall be able to be report other user profiles for these reasons: Disturbing other users, Sharing unrelated or disturbing posts, Spam, Fake Profile, Stolen Account .
  * **1.1.5.6.** Users shall be able to decide whether it shall appear or be hidden on the profile page's certain sections: Bio, Age, Gender, Affiliations

* #### 1.1.6. Search
   * **1.1.6.1.** Guests and users shall be able to search events, projects and other users with the search engine.
   * **1.1.6.2.** Users shall be able to utilize the advanced search facility to customize the search with filters.
   * <mark> **1.1.6.3.** Users should be able to sort search results 
     * **1.1.6.3.1** Users should be able to sort user-related search results with these criteria: alphabet order, connection with common teammates, rating.
     * **1.1.6.3.2** Users should be able to sort project-related search results with these criteria: alphabet order, relation with users' interest.
     * **1.1.6.3.3** Users should be able to sort event-related search results with these criteria: alphabet order, date, submission deadline. </mark>



* #### 1.1.7. Follow
   * **1.1.7.1.** Users shall be able to follow other users with public profile pages.
   * **1.1.7.2.** Users shall be able to send follow requests for private profile pages.
   * **1.1.7.3.** Users with private profile pages shall be able to accept or decline the following requests.
   * **1.1.7.4.** Users shall be able to keep track of the activities that belong to the followed users and collaborators.
   * **1.1.7.5.** Users shall be able to unfollow other users.


* #### 1.1.8. Project Page:
   * **1.1.8.1.** Project Coordinator shall be able to provide the information regarding project manually.
     * **1.1.8.1.1.** The coordinator shall be able to provide the title of project. 
     * **1.1.8.1.2.** The coordinator shall be able to provide the description of project.
     * **1.1.8.1.3.** The coordinator shall be able to provide the type of project that what project is for, such as conference, the project for an institution or journal.
     * **1.1.8.1.4.** The coordinator shall be able to provide the tags of project.
     * **1.1.8.1.5.** The coordinator shall be able to provide the due date of project.
     * **1.1.8.1.6.** The coordinator shall be able to provide the accessibility of project.
     * **1.1.8.1.7.** The coordinator shall be able to provide the state of project such as Open For Collaborators or Seeking For Collaborators, In Progress.
     * **1.1.8.1.8.** The coordinator shall be able to add people to the collaborators of project.
   * **1.1.8.2.** Those who're not collaborators can view the project's public information



### <a name="1-2-System-Requirements"></a> 1.2. System Requirements
* #### 1.2.1. Search Engine
  * **1.2.1.1** The system shall provide a search engine that supports basic and advanced search.
      * **1.2.1.1.1** Basic search shall support searching with name and tag.
      * **1.2.1.1.2** Advanced search shall support searching with the institution, rating, and skills for the user; project stage, due date and linked event for projects; date, submission deadline, location and type for events.
  * **1.2.1.2** Search engine shall support searching among user profiles, projects, and events.
    * **1.2.1.2.1** Search results shall include the public and followed profiles.
    * **1.2.1.2.2** Search results shall include public projects.
  * **1.2.1.3** Search engine shall support semantic search.
    * **1.2.1.3.1** Semantically related content about the search keywords shall be included in search results.
    
* #### 1.2.2. Recommendation
  * **1.2.2.1** System shall support a recommendation system to provide related content to users.
    * **1.2.2.1.1** Recommendation system shall be based on users' previous projects, interest areas, ratings, and skills.
    * **1.2.2.1.2** System shall recommend possible collaborators to project creators.
    * **1.2.2.1.3** System shall provide possible project recommendations to users.
    
* #### 1.2.3. Notifications
  * **1.2.3.1** System shall provide notifications about user feeds and updates on projects and events.
    * **1.2.3.1.1** System shall notify users in case of follows, follow requests and ratings on their profiles.
    * **1.2.3.1.2** System shall notify users in case of project collaboration requests and updates on milestones, files and polls about collaborated projects. 
    * **1.2.3.1.3** System shall notify users in case of stage changes about followed and collaborated projects. 

* #### 1.2.4. Profile Page
  * <mark> **1.2.4.1** System shall ensure that private profile pages are displayed to followers. </mark>
  * <mark> **1.2.4.2** System shall provide necessary mechanism for users to link their profile page with Google Scholar or ResearchGate. </mark> 

* #### 1.2.5. Project Structure
  * **1.2.5.1** Projects shall consist of stages "Open for collaborators", "In Progress", "Submitted to event", "Published".
  * **1.2.5.2** Projects should support stages "Cancelled", "Reopened".
  * **1.2.5.3** System shall provide a text editor to edit project files.
  * **1.2.5.4** System shall support an upload file functionality for the files that are less than 5MBs.
  * **1.2.5.5** System shall make private projects to be visible by only the collaborators.
* #### 1.2.6. Project Page
  * **1.2.6.1** System shall provide a project page to the collaborators to be viewed by all the users in the system. 
  * **1.2.6.2** System shall ensure that project's private information isn't shown to those who are not collaborators.
  * **1.2.6.3** System shall ensure that guest users can not collaborate or request collaboration.
  * **1.2.6.4** System shall provide necessary mechanism for users to link their profiles with the project. 
 ## <a name="2-Non-Functional-Requirements"></a> 2. Non-Functional Requirements
### <a name="2-1-Performance-Requirements"></a> 2.1.Performance Requirements
  * **2.1.1.**  Respond times should be less than 3 seconds.
  * **2.1.2.**  The system should work 7/24 with no more than 1% downtime.
### <a name="2-2-Legal Constraints"></a> 2.2.Legal Constraints
  * **2.2.1.**  Not allowed the processing of personal data outside the legitimate purpose for which the personal data was collected.
  * **2.2.2.** Personal data shall be deleted once the legitimate purpose for which it was collected is fulfilled.
  * **2.2.3.** The controller of personal data has the accountability to ensure that personal data is protected and GDPR requirements respected, even if the processing is being done by a third party.
  * **2.2.4.** Intention to process personal data beyond the legitimate purpose for which that data was collected, a clear and explicit consent must be asked from the data subject.
### <a name="2-3-Security-Requirements"></a> 2.3.Security Requirements
  * <mark> **2.3.1.**  System should store hashed passwords. </mark>
  * **2.3.2.**  System objects should be encrypted with MD5.
  * **2.3.3.**  System should use HTTPS Protocol.
  * **2.3.4.**  System should be backed up to AWS at the end of each day.
### <a name="2-4-Portability-Requirements"></a> 2.4.Portability Requirements
   * **2.4.1.** Mobile Application should support Android 6 or later.
   * **2.4.2.** Mobile Browsers shall redirect to the mobile application.
   * **2.4.3.** Web Application should support Chrome, Firefox, Safari or Opera.
### <a name="2-5-Implementation-Requirements"></a> 2.5.Implementation-Requirements
   * **2.5.1.** The application should be dockerized.
   * **2.5.2.** The implementation of the system should follow W3C standards and W3C Activity Streams Protocol.
   * **2.5.3.** There shall be a web platform and a native Android application that supports the same functionalities.
   * **2.5.4.** The color designs of the platform will be made to improve the experience of color-blind people.
   
# Assessment of the customer presentation

The presentation helped us set a clear direction for our project thanks to the many insigthful feedbacks and suggestions we got from our customers. The reaction from our customers was generally positive as they were following the presentation, and at the end our customer came up with good questions about our path moving forward. The main focus of the customer was the process of joining a project. Although there were some mixed opinions, the customer generally wanted the project owner to describe clear requirements and the collaborators to fit these requirements in order to join the project. This was a thought-provoking idea and led to some internal discussions in the team and has been helpful as it made the product clearer before further development. 

During our earlier customer meetings, our customer warned us to include interesting functinalities to make sure we are on the same page about important features. So we included important functionalities like project creation, profile creation and profile view. In the presentation, our team did a good job coming up with realistic scenarios, and displaying the strong points of our product. We managed the time well and captured the attention of the audience with interesting scenarios. What helped us in this process was that we practiced the presentation earlier internally and made sure everything was working as planned. The lesson we learned for the next time is that we should focus more on the Frontend and communicate more across teams, like Android, Frontend and Backend.