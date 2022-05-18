set terminal png size 1400,1000 enhanced font ",20"
set output "DemoMultiplot.png"

# a very simple plot functionality
set multiplot layout 3, 1 title "Multipe Plots"

# option for all plots
set grid
set xlabel "x values" font ",12" color rgb "#0000ff"
set ylabel "y values" font ",12"
set xrange[-2*pi:2*pi]
set yrange[-1:1]

# define three linestyles
set style line 1 linecolor rgb "#ff0000"
set style line 2 linecolor rgb "#0000ff"
set style line 3 linecolor rgb "#00ff00"

# plot of the sinus
set title "Sinus" font ",14"
plot sin(x) linestyle 1

# plot of the cosinus
set title "Cosinus" font ",14"
plot cos(x) with impulses linestyle 2

# plot of the tangens
set title "Tangens" font ",14"
plot tan(x) with filledcurve linestyle 3
