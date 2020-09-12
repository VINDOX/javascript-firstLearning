//var firstname='john';
//console.log(firstname);

/*
**variables and tadatypes

var lastname='smith';
var age=28;
var fullage=true;
console.log(fullage);
var job;

job='teacher';
console.log(job);

var years=3;
console.log(years);
//variables naming rules
*/
/*var firstname='john';
var age=28;
console.log(firstname + '  ' + age);
var job,ismarried;
job='teacher';
ismarried='false';
console.log(firstname + '  ' +  'is a' + '  ' + age + '  '+ 'years old' + ' '+',' + job + '  '+'is he married?' + ' ' + ismarried);

//variable mutation
 age='twenty eight';
 job='driver';
 alert(firstname + '  ' +  'is a' + '  ' + age + '  '+ 'years old' + ' '+',' + job + '  '+'is he married?' + ' ' + ismarried);

var lastname=prompt('what is his lastname');
console.log(firstname + ' '+ ' '+ lastname);
*/


// BASIC math operator

/*var year=2020;
var yearjohn=year-2;
var yearmark=year-3;
console.log(yearjohn,+' ' + yearmark );
console.log(yearjohn+2);

// basic logical operator
 var johnolder=yearjohn>yearmark;
 console.log(johnolder);

 // typeof operator

 console.log(typeof johnolder);
  console.log(typeof yearjohn);
 console.log(typeof married);
*/
 // ooperator precendeces
 /*var now=2018;
 var yearjohn=1989;
 var fullage=18

 var isfullage=now-yearjohn>=fullage;
 console.log(isfullage);

 var x,y;
 x=y=(3+5)*4-6;
 console.log(x,y);*/

 /*var markmass,johnmass,markheight,johnheight,mass,height;
 var bmi;
 markmass=2;
 johnmass=3;
 markheight=4;
 johnheight=5;
 //mass=10;
 bmi=markmass/(markheight*markheight);
bmi1=johnmass/(johnheight*johnheight);
 

console.log('please enter marks mass and height =',+ ' ' + bmi );

console.log(' john mass and height =',+ ' ' + bmi1 );

//console.log(markmass>johnmass);
var check;
check=(60-1)>markheight;
console.log('is marks bmi higher than johns?' + check);
*/


/*var a,b;
alert('please enter your age');
var a=prompt('enter number of a');
alert('please enter your another age');
var b=prompt('enter number of b');
console.log('you\'re entered a as =' + ' ' + a);
console.log('you\'re entered b as =' + ' ' + b);
if (a<b) {
	
	console.log('you re right because' + ' ' + 'a<b');}

	else if (a>=2 && b<20) {
console.log('teenager');
	}
else
{
	console.log('try again, you re wrong' + ' ' + 'a>b');
}
// ternary operator
var age;
var der=prompt('please enter any value');
console.log(der);
var drinks=der>=18 ? 'beer' : 'juice';
console.log(drinks);

// switch statment

var x=prompt('enter x value');
console.log(x);
switch(true){
case x<18 :
console.log('kids');
break;
case x<=18:
console.log('teenager');
break;
case x>=18:
console.log('old');
break;
default:
console.log('not exist');
}

*/

/*
var a=89;
var b=120;
var c=103;
var mark=(a+b+c)/3;


var x=116;
var y=94;
var z=123;

var john=(x+y+z)/3;

console.log('mark average is' + ' ' + mark);
console.log('john average is' + ' ' + john);

if (mark<john) {
console.log('the highest average scored is =' + ' ' + john);
console.log('winner team is john team');
}
else if (john<markmass) {
	console.log('the highest average scored is =' + ' ' + mark);
console.log('winner team is mark team');
}
 if (mark===104 && john===111) {
	console.log('you re changed, the scored goal are equal');
}*/

/*
// FUNCTIONS!!

function calc1(x){
	return 2020-x;
}

var n=calc1(2);
var t=calc1(3);
console.log(n,t);

// ARRAYS
var john=['john','EMMY',1990,'TEACHER'];
console.log(john[0],john[1],john[3]);

john.push('byose');//.push *add new value in front of last word*

john.unshift('a.k.a');//*add new value from the begening*
john.pop();//remove last value
john.shift();//remove value from the begening
console.log(john);

*/

 //OBJECT LETELALY

/*var kaz={
	firstname:'EMMY',
	lastname:'KAZINA',
	years:1994
};
console.log(kaz.firstname);
console.log(kaz.years);

//new object syntax

var kaz1 = new object();
kaz1.firstname = 'benoit';
kaz1.lastname = 'mugabo';
console.log(kaz1);

*/

// LOOPING >>for 
//for (var i = 1; i <6; i++) {

	//EVEN AND ODD NUMBER
/*
	var i=prompt('enter any number');
	if (i%2==0) {
		console.log('even number');
	}
		else {
			console.log('number are odd');
	}

	//for loop

var di=['emmy','dela','bobo']
	for (var i = 0; i < di.length; i++) {
		console.log (di[i]);
	}
	*/

//while loop
/*
var D=prompt('PLEASE ENTER YOUR NUMBER');
var di=['emmy','dela','bobo']
var i=0;
while(i<di.length){
	console.log(di[i]);
	i++;
	}

	*/

	/*var k=['vindo','aime'];
	console.log(k[0],k[1]);
	 k.push('people');
	 k.push('are');
	 k.unshift('ready');
	 console.log(k);

*/
	 
/*var bb='hi';
	 first();
	 function first(){
		 var cd='hello';
		 second();
	function second(){
		var de='hey';
	
		console.log(bb+ ' '+' '+cd+' '+de);
	}}
	*/
	/*
var a='hi';
var b='hello';
var c='hey';
function ka(a,b,c){
	return(a+b+c);
}

var ka=console.log(c );
console.log(b);
console.log(c);

*/

 // power of a number
 
 function add(a,b){
	 return a+b; 
 }

 let firstNumber,secondNumber,sum;
 firstNumber= prompt("Enter First Number","");
 secondNumber= prompt("Enter secondNumber Number","");
 firstNumber=Number(firstNumber);
 secondNumber=Number(secondNumber);
 sum = add(firstNumber,secondNumber);
 alert(sum);


// var po=prompt('enter Any number');
// console.log('YOU HAVE entered' + ' ' +po);
// var ex=prompt('please enter number to be powed');
// console.log('the number TO POWED IS'+' '+ex);
// console.log('RESULT IS');
// var pw=Math.pow(po,ex);
// console.log(pw);
