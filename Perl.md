### ✅ **1. Basic Hello World**

```perl
#!/usr/local/bin/perl
print "Welcome to perl";
```

---

### ✅ **2. Syntax Check Without Execution**

```perl
#!/usr/local/bin/perl -c
print "welcome to perl";
```

---

### ✅ **3. Scalar String Operations**

```perl
#!/usr/bin/perl
use strict;
use warnings;
my $str = "Hello, Krishnal!\n";
my $length = length($str);
print "Length of \$str: $length\n";
chomp($str);
print "After chomp, \$str: $str\n";
my $upper_case = uc($str);
my $lower_case = lc($str);
print "Upper case: $upper_case\n";
print "Lower case: $lower_case\n";
```

---

### ✅ **4. Undef and Defined Example**

```perl
#!/usr/bin/perl
use strict;
use warnings;
my $name = undef;
if (defined $name) {
  print "\$name is defined: $name\n";
} else {
  print "\$name is undefined.\n";
}
$name = "Krishna!";
if (defined $name) {
  print "\$name is defined: $name\n";
} else {
  print "\$name is undefined.\n";
}
```

---

### ✅ **5. Escape Sequences Showcase**

```perl
#!/usr/bin/perl
$my_var = "5 is different from \"five\"\n";
print $my_var;
print "Do you hear the bells?\a\n";
print "Do you see the ga\t\tp?\n";
print "The odd \uone out\n";
print "MY \1NEW GUITAR!\n";
print "the next \Ucase\n";
print "THE LAST ALCASE\n";
print "Q!@#^&* +\n";
```

---

### ✅ **6. Print with `qw` and Custom Formatting**

```perl
#!/usr/local/bin/perl
print("sachin", "dravid", "ganguly", "kumble", "\n");
print qw(sachin dravid ganguly kumble);
print "\n";
print "sachin", "dravid", "ganguly", "anil kumble", "\n";
print('k', 'a', qw(c p e r), 't', 'e', 'c', 'h');
print "\n";
```

---

### ✅ **7. Join and Split**

```perl
print join(" ", ("perl", "is", "a", "scripting", "language"));
```

```perl
$var = "where-there-is a-will,there-is a-way";
print split("-", ("$var"));
```

---

### ✅ **8. `map` and `grep`**

```perl
print join(", ", (map lc, 'A', 'B', 'C'));
```

```perl
print grep(!/x/, 'a', 'b', 'x', 'd');
```

---

### ✅ **9. Mapping and ASCII Conversion**

```perl
#!/usr/bin/perl
my @num = (65, 66, 67, 68);
my @num2 = map { 2 * $_ } @num;
my @char = map { chr($_) } @num;
print "@num2 \n@char\n";
```

---

### ✅ **10. Sorting Strings and Numbers**

```perl
#!usr/local/bin/perl
@str = qw(sachin dravid ganguly kumble);
@val = (56, 13, 45, 11);
@str_sort2 = sort { $a cmp $b } @str;
@str_rev = sort { $b cmp $a } @str;
@val_sort2 = sort { $a <=> $b } @val;
@val_rev = sort { $b <=> $a } @val;
print "@str_sort2\t@str_rev\n";
print "@val_sort2\t@val_rev\n";
```

---

### ✅ **11. Hash Example**

```perl
#!/usr/bin/perl
%hash1 = ( "one" => 1 ,"two" => 2 ,"three" =>3 ,"four" =>4);
print %hash1;
print "@{[values %hash1]}\n";
print "@{[keys %hash1]}\n";
@temp = %hash1;
print "@temp\n";
```

---

### ✅ **12. Sorting a Hash by Keys and Values**

```perl
%data = (
  sachin => 10,
  dravid => 19,
  dhoni  => 7,
  rohit  => 45
);
foreach $key (sort(keys(%data))) {
  print "\t$key \t $data{$key}";
}

foreach $key (sort { $data{$a} <=> $data{$b} } keys %data) {
  print "\t$key\t\t$data{$key}\n";
}
```

---

### ✅ **13. `if-elsif-else`**

```perl
$var = 2;
if ($var == 1) {
  print "we are in first if\n";
} elsif ($var == 2) {
  print "we are in second if\n";
} else {
  print "we are in third if\n";
}
```

---

### ✅ **14. `until` and `do-until` Loops**

```perl
$firstVar = 10;
do {
  print("inside: firstVar = $firstVar\n");
  $firstVar--;
} until ($firstVar < 2);
print("outside: firstVar = $firstVar\n");
```

```perl
#!/usr/bin/perl
$firstVar = 10;
until ($firstVar >= 20) {
  print("inside: firstVar = $firstVar\n");
  $firstVar++;
}
print("outside: firstVar = $firstVar\n");
```

---

### ✅ **15. `for`, `foreach`, and `last`, `next`, `redo` Keywords**

```perl
#!/usr/bin/perl
for ($firstVar = 0; $firstVar < 100; $firstVar++) {
  print("inside: firstVar = $firstVar\n");
}
```

```perl
#!/usr/bin/perl
@array = (1..5, 5..10);
print("@array\n");
foreach (@array) {
  $_ = "**" if ($_ == 5);
}
print("@array\n");
```

```perl
#!/usr/bin/perl
@array = ("A".."Z");
for ($index = 0; $index < @array; $index++) {
  last if ($array[$index] eq "T");
  print("$index\n");
}
```

```perl
#!/usr/bin/perl
@array = (0..9);
for ($index = 0; $index < @array; $index++) {
  next if ($index == 3 || $index == 5);
  $array[$index] = "*";
}
print("@array\n");
```

```perl
#!/usr/bin/perl
while (1) {
  print("What is your name? ");
  $name = <STDIN>;
  chop($name);
  if (!length($name)) {
    print("Msg: Zero length input. Please try again\n");
    redo;
  }
  print("Thank you, " . uc($name) . "\n");
  last;
}
```

---

### ✅ **16. Regex: Match & Substitution**

```perl
#!usr/local/bin/perl
$_ = "success is a progressive journey";
$var = "success is not a destination";

if (/success/) {
  print "String success Found";
}

if ($var =~ /destination/) {
  print "String destination Found";
}
```

```perl
#!usr/local/bin/perl
$txt = "winn-ers see ga-in, lose-rs see pa-in";
while ($txt =~ m/-/g) {
  print "Found another -\n";
}
```

```perl
#!usr/local/bin/perl
$text = "winners see gain, losers see pain";
$text =~ s/winners/WINNERS/;
print $text;
```

```perl
#!usr/local/bin/perl
@arr = qw(sachin dravid ganguly sachin);
foreach (@arr) {
  s/sachin/10/;
}
print "\n@arr";
```

```perl
#!usr/local/bin/perl
@old = qw(sachin-bharat dravid-bharat ganguly-bharat kumble-ind);
for (@new = @old) {
  s/bharat/india/;
}
print "@old\n";
print "@new\n";
```

---

### ✅ **17. Regex: Translation (tr///)**

```perl
#!usr/local/bin/perl
$text = "winners see gain, losers see pain";
$count = ($text =~ tr/e/E/);
print $text;
print "\n no..of replacements = $count";
```

```perl
#!usr/local/bin/perl
$text = "winners see gain, losers see pain";
$count = ($text =~ tr/e/E/c);
print $text;
print "\n no..of replacements = $count";
```

---

### ✅ **18. Regex: Alternate Delimiters and Modifiers**

```perl
#!usr/local/bin/perl
$var = "winners / see / gain,losers / see pain";

if ($var =~ m|see|) {
  print "String see Found\n";
}

if ($var =~ m?gain?) {
  print "String gain Found";
}
```

---

### ✅ **19. Regex: Greedy vs Non-Greedy Quantifiers**

```perl
#!usr/local/bin/perl
$text = "They are players ,aren’t they?";
$text =~ s/.*?are/were/;
print $text;
```

```perl
#!usr/local/bin/perl
$txt = "no, these are the documents, over there.";
$txt =~ s/the(.*?)e/those/;
print $txt;
```

---

### ✅ **20. Regex: Metacharacter Matching**

```perl
#!usr/local/bin/perl
$text = "#25 \$finish";
$text =~ s/#25/\/\/#25/;
print $text;
```

```perl
#!usr/local/bin/perl
$text = "//#25 \$finish";
$text =~ s/\/\/#25/#25/;
print $text;
```

---

### ✅ **21. Regex: Word Boundary Assertions**

```perl
#!usr/local/bin/perl
$text = "one, ****, three, four"; 
$text1 = "one,****,three, four";

foreach ($text =~ /\b\w+\b/g) {
  print $_, "\t";
}

print "\n using \\B\n";

foreach ($text1 =~ /\B\w+\B/g) {
  print $_, "\t";
}
```

---

### ✅ **22. Subroutines – Basic, With Return, and Arguments**

```perl
#!usr/local/bin/perl
$v1 = 36;
large_small();                 # subroutine before definition
sub large_small {
  if ($v1 > 40) {
    print "value is bigger than 40\n";
  } else {
    print "value is smaller than 40\n";
  }
}
$v1 = 45;
large_small;
```

```perl
#!usr/local/bin/perl
$v1 = 36;
incr();
print "value of v1 = $v1\n";
sub incr {
  my $var = $v1;
  print "value before incrementing = $var\n";
  $var++;
  print "value after incrementing = $var\n";
}
```

```perl
#!usr/local/bin/perl
$sum = add(10, 20);
print "sum = $sum\n";
sub add {
  ($val1, $val2) = @_;
  return $val1 + $val2;
}
```

```perl
sub my_sub {
  print "@_";
}
my_sub(1, 2, 3);
```

```perl
#!/usr/bin/perl
sub greet_name {
  my ($name) = @_;
  print "Hello, $name!\n";
}
greet_name("John");
```

---

### ✅ **23. Subroutines – Pass by Reference**

```perl
#!/usr/bin/perl
sub modify_scalar {
  my ($scalar_ref) = @_;
  $$scalar_ref *= 2;
}
my $number = 5;
modify_scalar(\$number);
print $number; # Output: 10
```

```perl
#!/usr/bin/perl
sub append_element {
  my ($array_ref, $element) = @_;
  push @$array_ref, $element;
}
my @numbers = (1, 2, 3);
append_element(\@numbers, 4);
print join(", ", @numbers); # Output: 1, 2, 3, 4
```

```perl
#!/usr/bin/perl
sub add_key_value {
  my ($hash_ref, $key, $value) = @_;
  $hash_ref->{$key} = $value;
}
my %person = (name => "Alice", age => 30);
add_key_value(\%person, "city", "New York");
print $person{city}; # Output: New York
```

```perl
#!/usr/bin/perl
sub create_array {
  my @new_array = (5, 6, 7);
  return \@new_array;
}
my $array_ref = create_array();
print join(", ", @$array_ref); # Output: 5, 6, 7
```

```perl
#!usr/local/bin/perl
@arr = qw(America England france);
print "before: arr = " . join(', ', @arr) . "\n";
change(\@arr);
print "after: arr = " . join(', ', @arr) . "\n";
sub change {
  my $ref_arr = shift;
  $$ref_arr[0] = "China";
  @{$ref_arr}[1] = "India";
  $ref_arr->[2] = "Japan";
}
```

---

## ✅ 24. Scalar Variables – Creation & Manipulation

```perl
#!/usr/bin/perl
use strict;
use warnings;

# Scalar Variable Examples
my $num = 42;                # Numeric scalar
my $str = "Hello";           # String scalar
my $concat = $str . " World"; # String concatenation
my $sum = $num + 8;          # Numeric operation

# Special Variables
$_ = "Perl is fun!";         # Default variable used by many functions
print "Using \$_: $_\n";

# Print results
print "Number: $num\n";
print "Concatenated String: $concat\n";
print "Sum: $sum\n";

# $. represents the current line number in a file
open(my $fh, "<", "input.txt") or die "Cannot open file!";
while (<$fh>) {
    print "Line $.: $_";
}
close($fh);
```

---

## ✅ 25. Context Sensitivity in Perl

```perl
#!/usr/bin/perl
use strict;
use warnings;

my @arr = (1, 2, 3, 4, 5);
my $scalar = @arr;  # Scalar context - number of elements
my @list = @arr;    # List context - entire array

print "Scalar Context: $scalar\n"; # Output: 5
print "List Context: @list\n";     # Output: 1 2 3 4 5

# String vs. Numeric Context
print "String Context: " . ("10" . "20") . "\n";  # Concatenation => 1020
print "Numeric Context: " . ("10" + "20") . "\n"; # Arithmetic => 30
```

---

## ✅ 26. Array Operations and Slices

```perl
#!/usr/bin/perl
use strict;
use warnings;

my @arr = (10, 20, 30, 40, 50);
push(@arr, 60);     # Add element to end
pop(@arr);          # Remove last element
unshift(@arr, 5);   # Add at beginning
shift(@arr);        # Remove first element

# Array Slices
my @slice = @arr[1, 3];

print "Modified Array: @arr\n";
print "Array Slice: @slice\n";
```

---

## ✅ 27. File I/O – Uppercase Transformation

```perl
#!/usr/bin/perl
use strict;
use warnings;

open(my $in, "<", "input.txt") or die "Cannot open file!";
open(my $out, ">", "output.txt") or die "Cannot write to file!";

while (my $line = <$in>) {
    print $out uc($line);  # Convert to uppercase
}

close($in);
close($out);
print "File processing completed.\n";
```

---

## ✅ 28. Regex – Email Replacement

```perl
#!/usr/bin/perl
use strict;
use warnings;

open(my $in, "<", "emails.txt") or die "Cannot open file!";
open(my $out, ">", "output.txt") or die "Cannot write to file!";

while (my $line = <$in>) {
    $line =~ s/\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}\b/[REDACTED]/g;
    print $out $line;
}

close($in);
close($out);
print "Email addresses masked.\n";
```

> 📌 **Regex Explanation**:  
> Matches standard email addresses and replaces them with `[REDACTED]`.

---

## ✅ 29. Pascal’s Triangle

```perl
#!/usr/bin/perl
use strict;
use warnings;

print "Enter the number of rows for Pascal's Triangle: ";
my $n = <STDIN>;
chomp($n);

for (my $i = 0; $i < $n; $i++) {
    my $num = 1;
    for (my $j = 0; $j <= $i; $j++) {
        print $num . " ";
        $num = $num * ($i - $j) / ($j + 1);
    }
    print "\n";
}
```

---

## ✅ 30. Email Validator

```perl
#!/usr/bin/perl
use strict;
use warnings;

print "Enter an email: ";
my $email = <STDIN>;
chomp($email);

if ($email =~ /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/) {
    print "Valid Email: ", lc($email), "\n";
} else {
    print "Invalid Email\n";
}
```

---

## ✅ 31. Prime Numbers in a Range

```perl
#!/usr/bin/perl
use strict;
use warnings;

print "Enter start and end range: ";
my ($start, $end) = split(/\s+/, <STDIN>);

sub is_prime {
    my ($num) = @_;
    return 0 if $num < 2;
    for my $i (2 .. sqrt($num)) {
        return 0 if $num % $i == 0;
    }
    return 1;
}

print "Prime numbers: ";
foreach my $n ($start .. $end) {
    print "$n " if is_prime($n);
}
print "\n";
```

---

## ✅ 32. Reverse Words in a Sentence

```perl
#!/usr/bin/perl
use strict;
use warnings;

print "Enter a sentence: ";
my $sentence = <STDIN>;
chomp($sentence);

my $reversed = join(" ", reverse split(/\s+/, $sentence));
print "Reversed: $reversed\n";
```

---

