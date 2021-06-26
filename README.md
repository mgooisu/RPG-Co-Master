# TableTop_Counter_Edit
An applet for creating and managing arrays of encounters organized at different scopes.
<h1> The aim </h1>

This application seeks to simplify the process of creating and managing combat encounters in dungeons and dragon's 5th edition. It will allow for management of encounters at several scopes.
<h1>Object Requirements :</h1>

<h2> Creatures </h2>
Contain the base data structures required by all creatures - e.g base armor class, hp ect. All information that would be included in a combat encounter must be specified for this object to exist.
<h3> Characters</h3>
Contain the specific information pertaining to the "human" characters in the story, including their brief history and a log of events that pertain to them over the course of the campaign
<h4> PCs </h4>
Includes all of the additional information that would normally be contained on a character sheet and simple accessor handles that allow the user interface to add items or expend spell slots, along with anyother dynamic changes to the state of the player character object over the course of play. Another feature will be a encounter end\session end difference display, so the game master can easily see what the resulting state of each player's sheet should be.

<h4> NPCs </h4>
Includes information about the characters name, occupation, class, abilities, stake in the story ect. Will also include an autogeneration tool which will allow the user to specify certain values for the character and randomly generate the others. Could also plug into an autogenerator api into it for on-the fly descriptions.<break/>
Descriptions of important characters should be stored at a chapter or campaign scope basis so the character is consistant with players.

<h4> TODO </h4> 
A general Description of all of the character ojects that are included in the operation of the application and what it does

<h1> Scope-Wise management </h1>
<h2> Campaign </h2>
Users can create creatures and sets of encounters as part of an entire campaign. This is also the scope in which user characters and ongoing npcs will be defined.
<h2> Chapter</h2>
Users can create semi-permanent objects that are related to only a section of the capaign. e.g collections of encounters tied to certain events/ares, area or arc specific characters or anything that would only be interacted with in a section of the campaign.
<h2> Session </h2>
Users can create objects that only exist in the context of a single session. E.g. Encounters, short-term characters ect.
