set terminal png size 1400,1000 enhanced font ",20"
set output "DemoDataContained.png"

$DemoData << EOD
1 10
2 20
3  5
4 15
5  8
6 17
7  2
EOD

set title "Data In Script"

set grid
set xlabel "x values"
set ylabel "y values"

set style line 1 linecolor rgb "#ff0000" linewidth 2
plot $DemoData using 1:2 with line linestyle 1 \
    title "Some Data"
