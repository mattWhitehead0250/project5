# project5

Matt Whitehead
Documentation

For the purposes of ordering and structuring the GUI, I used the WindowBuilder plugin. I attempted to get as close as possible to the example given and this proved tedious and complicated without the use of a plugin like this. 

For my creative idea, I used a series of 4 images that I located on the internet. Each image is a logo for various universities and it displays in the creative area upon click of the calculate HD button. This was implemented simply by adding a random number if elseif system to select the image randomly put this inside of the calculateHD button actionlistener. 

The ShowStation actionlistener just gets the value set on the slider then calls the calculateHammingDistance method to return a list of the stations of equal hamming distance. It then puts them in the stationList, which is later displayed in the textbox. 

The calculate hd button actionlistener does roughly the same thing, calling calculateHammingDistance to get the hamming distance of the station selected in the stationDropdown. It then sets the distance fields of distance 0,1,2,3, and 4 to the values stored at the respective indexes for the number of stations with equal hamming distance. 

the slider needed a changeListener to get the value of the slider when it's changed. it sets the value of the Hamming Distance text field above the slider to whatever is represented by the slider. 

I played with the size of the frame and settled on 800 x 600 because it fit the arrangement of the boxes well and looked similar to the example. The setup of the frame as well as placement of the panels on the frame just simply followed the examples from lab demos and class lectures that I had saved. 

Lines 209 through about 260 and about 300 through about 350 were generated by the WindowBuilder plugin for me, but it essentially just sets the parameters for me that orients the different components and panels in the frame, very similarly to how we did it it class. 

The Add Station button and it's listener just simply checked to see if the entered text string was already in the array of stations and if it wasn't then it made it uppercase, stored it in the array, then sorted it again. 

The calculateHammingDistance method was similar to the hammingDistance  calculation methods and classes from previous labs / projects and I coppied a lot of the work from there. It is a basic implementation of those methods and I don't think it's necessary to explain how it works again, though I can if necessary. 

The conclusion of it is the main method. I opted out of complicating the project with stretching it out across multiple classes. This is mainly because I used the WindowBuilder plugin which seemed complicated at first and I wanted to make sure I could make everything work   before trying to organize the project in a better way. I ultimately never went back and reorganized it because as they say, if it's not broke, don't fix it. 

Matt Whitehead
