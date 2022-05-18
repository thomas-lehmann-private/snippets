# Welcome to the Gnuplot Snippets

## Introduction

In general I'm trying to combine features
at different locations instead writing an
example for each individual feature. See
the descriptions below.

## Running Scripts

```bash
gnuplot DemoMultiplot.plt
gnuplot DemoDataContained.plt
```

## Export to png

In most cases the export to png is used that I'm
using as preview in my IDE. Please not that I got
better results with pngcairo; as an example the
**dashtype** seems not recognized with "png" only.

## DemoMultiplot

 - Three plots are shown (three rows)
 - Filled curves and impulse curves are shown
 - The x/y ranges are limited
 - The plot is using functions (sin, cos and tan)
   not data tables.
 - Label (axes) and title fonts are changed (size)
 - Options set before a plot are also available
   in next plot as long as not overwritten again
 - Plot is exported to png

## DemoDataContained

 - Possibility to have the data inside the script.
   For generating one script on the fly it might
   be useful.
 - **Label colors** of axes are changed
 - The **dashtype** is changed
 - A **trendline** is calculated and plotted
   (here using a linear function)
 - Grid is shown
 - Title color is changed
 - Plot is exported to png


## Useful links

 - http://www.gnuplot.info
