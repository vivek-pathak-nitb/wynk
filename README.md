# Overview
- Api's are written following rest paradigm.
- Spring boot has been used to write respective api's. Following are the three controllers
- ArtistController: Controller for artist resource.
- SongController: Controller for song resource.
- PlaylistController: Controller for playlist resource.

# How to run? 
  - run "mvn clean install" for building the project 
  - WynkApplication is the main class

# Exposed api's
Check respective controller's for detailed signature.
- /wynk/artist/follow - ArtistController
- /wynk/artist/unfollow - ArtistController
- /wynk/artist/popular - ArtistController
- /wynk/artist/follow/count - ArtistController
- /wynk/playlist - PlaylistController
- /wynk/song/popular - SongController
- /wynk/song/publish - SongController
 
