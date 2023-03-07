#!/usr/bin/env perl

### POBRANIE WSZYSTKIEGO Z DEBREVEN DO SLOWNIKA ###
my $bialka_hash;

open(source, "<", 'Seq_deBrevern.txt') or die("File not found");

for($i = 0; my $String = <source>; $i++) {
  if($i > 1) {         # omin 2. pierwsze linie
    $bialka_hash{substr($String, 0, 4)} = $String;
  }
}

close(source) or die("Source not closed");



### MAPA DO POBIERANIA FASTA ###

my $wszystkie_nazwy_bialek = "1Y7Q, 1F37, 1IDJ, 1EZG, 1BN8, 1CMB, 1JTA, 1M8B, 1AME, 1B7I, 1B7J, 1B7K,
1B8Z, 1BFM, 1C9O, 1EKL, 5XQP, 5XQV, 5XR0, 6MSI, 7AME, 7MSI, 8AME, 8MSI,
9AME, 9MSI, 1F4N, 1JAB, 2SPG, 1L0S, 1MSJ, 1N4I, 5MSI, 1UCS ,2MSI, 2MSJ,
2Q3A, 2VOC, 2WCI, 2ZU0, 3AME, 3MSI, 3QF6, 3VN3, 1O88, 1OOC, 1PLU, 1QRM,
1YES, 1Z2F, 2AME, 2BSP, 2FKO, 2JIA, 3WP6, 1XQ8,
2MPZ, 2MVX, 2MXU, 2N0A, 6HUD, 1DVQ, 1GKO, 1G1O, 4PWE, 6SDZ, 4BJL";

#usuniecie bialych znakow
$wszystkie_nazwy_bialek =~ s/\s+//g;

# podzial na tablice bialek
@tablica_wszystkich_nazw_bialek = split(',', $wszystkie_nazwy_bialek);

foreach my $aktualne_bialko (@tablica_wszystkich_nazw_bialek)
{
  use File::Fetch;
  my $url = 'https://www.rcsb.org/fasta/entry/'.$aktualne_bialko.'/display';
  my $ff = File::Fetch->new(uri => $url);
  my $file = $ff->fetch() or die $ff->error;
  print "Dane zapisane w pliku: ".$file."\n";
  open(my $f, "<".$file) or die("Brak pliku ".$file);
  my $cala_sekwencja_bialka = <$f>; # pomijamy pierwszy wiersz
  $cala_sekwencja_bialka = <$f>;
  close($f);
  print "Sekwencja: ".$cala_sekwencja_bialka."\n";

  #my $bialko = "GSKNCPDPELCRQSFRRFCYQEVSGPQEALSQLRQLCRQWLQPELHTKEQILELLVMEQFLTILPEEIQARVRHRCLMSSKEIVTLVEDFHRASKKPK";


  open(my $out, ">", "wyniki/".$aktualne_bialko."_res.csv") or die "Can't create output file";

  print $out $aktualne_bialko." ".$cala_sekwencja_bialka;

  for($i = 0; $i < length($cala_sekwencja_bialka) - 3; $i++) {
    print $out "\n";
    $czworka = substr($cala_sekwencja_bialka, $i, 4);
    if($linia = $bialka_hash{$czworka}){
      $linia =~ s/\t/,/g; # zamiana tabulatur na przecinki
      chomp($linia); # usuniecie nowej linii
      print $out $linia;
    } else {
      print $out $czworka;
    }
  }

  close $out or die "Output file not closed";
}

print "Success";
