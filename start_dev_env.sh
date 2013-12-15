START_DIR=/home/kosmos/PROJECTS/scala/compile/scalashell
cd ${STARTDIR}
xterm -T 'SBT Build Window' -fg Gray -bg black -en UTF-8 -bc -bcn 1500 -cr Green -e 'sbt' &
xterm -T 'Scala Code' -fg Gray -bg black -en UTF-8 -bc -bcn 1500 -cr Yellow &
xterm -T 'Utility Window' -fg Yellow -bg black -en UTF-8 -bc -bcn 1500 -cr Red &

