.data

L1:
.asciz "\nSquare of d larger than sum of squares\n\n"

L2:
.asciz "\nSquare of d smaller than sum of squares\n\n"

.text
.global main


Compute_1:
stmfd sp!,{fp,lr,v1,v2,v3,v4,v5}
add fp,sp,#24
sub sp,fp,#28
add v3,a2,a3
mov a1,v3
b .L4exit
.L4exit:
sub sp,fp,#24
ldmfd sp!,{fp,pc,v1,v2,v3,v4,v5}


Compute_2:
stmfd sp!,{fp,lr,v1,v2,v3,v4,v5}
add fp,sp,#24
sub sp,fp,#48
ldr a3,[a1,#4]
cmp a3,#0
beq .3
ldr a1,[a1,#0]
mov a1,a1
b .L3exit
b .4

.3:
mov v5,#1
mov v5,v5
str v5,[a1,#4]
mov a1,a1
mov a2,a2
bl Compute_0(PLT)
mov a3,a1
str a3,[fp,#-44]
mov a1,a1
ldr a2,[fp,#-36]
bl Compute_0(PLT)
mov v5,a1
mov a1,a1
ldr a2,[fp,#-44]
mov a3,v5
bl Compute_1(PLT)
mov a2,a1
mov a1,a2
b .L3exit

.4:
.L3exit:
sub sp,fp,#24
ldmfd sp!,{fp,pc,v1,v2,v3,v4,v5}


Compute_0:
stmfd sp!,{fp,lr,v1,v2,v3,v4,v5}
add fp,sp,#24
sub sp,fp,#28
mul a4,a2,a2
mov a1,a4
b .L2exit

.L2exit:
sub sp,fp,#24
ldmfd sp!,{fp,pc,v1,v2,v3,v4,v5}


main:
stmfd sp!,{fp,lr,v1,v2,v3,v4,v5}
add fp,sp,#24
sub sp,fp,#68
mov v5,#1
mov a4,v5
mov v5,#2
mov a3,v5
mov v5,#3
mov a2,v5
mov v5,#4
mov v1,v5
str a4,[fp,#-28]
str a3,[fp,#-32]
str a2,[fp,#-36]

/*this section here is for new Compute() object*/
mov a1,#8
bl malloc(PLT) 
mov a4,a1

str a4,[fp,#-52]
ldr a1,[fp,#-52]
ldr a2,[fp,#-28]
ldr a3,[fp,#-32]
bl Compute_2(PLT)
mov v5,a1

ldr a1,[fp,#-52]
ldr a2,[fp,#-36]
bl Compute_0(PLT)
mov a4,a1

add a1,v5,a4
str a1,[fp,#-44]
ldr a1,[fp,#-52]

mov a2,v1
bl Compute_0(PLT)
mov a3,a1
ldr a4,[fp,#-44]
cmp a3,a4
movgt a1,#1
movle a1,#0
cmp a1,#0
beq .1
ldr a1,=L1
bl printf(PLT)
b .2

.1:
ldr a1,=L2
bl printf(PLT)

.2:
.L1exit:
mov a4,#0
mov a1,a4
sub sp,fp,#24
ldmfd sp!,{fp,pc,v1,v2,v3,v4,v5}