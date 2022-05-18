#
# The MIT License
#
# Copyright 2022 Thomas Lehmann.
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in
# all copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
# THE SOFTWARE.
#
set terminal pngcairo size 1400,1000 enhanced font ",20"
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
