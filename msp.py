from bs4 import BeautifulSoup
from urllib import urlopen
import sys

queries = 0
while queries <1:
	stringQ = sys.argv[1]
	page = urlopen('http://www.yelp.com/biz/' + stringQ)

	soup = BeautifulSoup(page)
	reviews = soup.findAll('p', attrs={'itemprop':'description'}, limit=5)


	flag = True
	indexOf = 1

	for review in reviews:
		dirtyEntry = str(review)
		while dirtyEntry.index('<') != -1:
			indexOf = dirtyEntry.index('<')
			endOf = dirtyEntry.index('>')
			if flag:
				dirtyEntry = dirtyEntry[endOf+1:]
				flag = False
			else:
				if(endOf+1 == len(dirtyEntry)):
					cleanEntry = dirtyEntry[0:indexOf]
					break
				else:
					dirtyEntry = dirtyEntry[0:indexOf]+dirtyEntry[endOf+1:]
		print cleanEntry
		


	queries = queries + 1
