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
set output "DemoDataContained.png"

$DemoData << EOD
1 10
2 20
3  5
4 15
5  8
6 11
7 17
8  2
EOD

# define line style(s)
set style line 1 linecolor rgb "#ff0000" \
    dashtype ".--" linewidth 2
set style line 2 linecolor rgb "#0000ff" \
     linewidth 1
# define line type(s)
set linetype 1 linecolor rgb "#0000ff"
set linetype 2 linecolor rgb "#008000"

set title "Data In Script" textcolor lt 2

set grid
set xlabel "x values" textcolor lt 1
set ylabel "y values" textcolor lt 1

f(x) = m*x + c
fit f(x) '$DemoData' using 1:2 via m, c

# plot the script data
plot $DemoData using 1:2 with lines linestyle 1 \
    title "Some Data", \
    f(x) with lines linestyle 2 \
    title "Trendline"
