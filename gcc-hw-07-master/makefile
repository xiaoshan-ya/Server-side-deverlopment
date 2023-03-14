hw:t1.o t2.o
	gcc -o hw t1.o t2.o

t1.o:t1.c
	gcc -g -Wall -c -o t1.o t1.c

t2.o:t2.c
	gcc  -g -Wall -c -o t2.o t2.c

#  .PHONY : clean
clean:
	# rm t1.o t2.o hw
	del *.o
	del hw.*

exec:
	cd /codec/temp;pwd
