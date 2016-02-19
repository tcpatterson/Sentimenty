import csv
with open( "dbHistorical.csv","rb" ) as source:
    rdr = csv.reader( source )
    with open ("dbHistoricalParsed.csv", "wb") as result:
        wtr = csv.writer( result )
        for r in rdr:
            wtr.writerow( (r[0], r[4]) )
