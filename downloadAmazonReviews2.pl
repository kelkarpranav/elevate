#!/usr/bin/perl

# name: Amazon reviews downloader
# author: Andrea Esuli
# website: http://www.esuli.it
# date: January 2012
# license: please cite and put a link to the source if you use it. Modify and redistribute as you want.
# usage: ./downloadAmazonReviews.pl <domain> <list of IDs of amazon products>
# example: ./downloadAmazonReviews.pl com B0040JHVC2 B004CG4CN4
# output: a directory ./amazonreviews/<domain>/<ID> is created for each product ID; HTML files containing reviews are downloaded and saved in each directory.

use strict;
use LWP::UserAgent; 
use HTTP::Request;

$| = 1; #autoflush

my $ua = LWP::UserAgent->new;
$ua->timeout(10);
$ua->env_proxy;
$ua->agent('Mozilla/5.0 (X11; Linux i686) AppleWebKit/534.30 (KHTML, like Gecko) Ubuntu/11.04 Chromium/12.0.742.91 Chrome/12.0.742.91 Safari/534.30');



my $sleepTime = 1;

my $domain = shift;


my $id = "";
while($id  = shift) {



    my $urlPart1 = "http://www.amazon.".$domain."/product-reviews/";
    my $urlPart2 = "/?ie=UTF8&showViewpoints=0&pageNumber=";
    my $urlPart3 = "&sortBy=byRankDescending";

    my $referer = $urlPart1.$id.$urlPart2."1".$urlPart3;

    my $page = 2;
    my $lastPage = 1;
    my $one = 1;
    my $two = 2;
    while($one<$two) {

	my $url = $urlPart1.$id.$urlPart2.$page.$urlPart3;

	print $url;

	my $request = HTTP::Request->new(GET => $url);
	$request->referer($referer);

	my $response = $ua->request($request);
	if($response->is_success) {
	    print " GOTIT\n";
	    my $content = $response->decoded_content;

	    if(open(CONTENTFILE, ">./$page")) {
		print CONTENTFILE $content;
		close(CONTENTFILE);
		print "ok\t$domain\t$id\t$page\t$lastPage\n";
	    }
	    else {
		print "failed\t$domain\t$id\t$page\t$lastPage\n";
	    }

	    while($content =~ m#cm_cr_pr_top_link_([0-9]+)#gs ) {
		my $val = $1+0;
		if($val>$lastPage) {
		    $lastPage = $val;
		}
	    }
	    
	    if($sleepTime>0) {
		--$sleepTime;
	    }
	}
	else {
	    print " ERROR ".$response->code;
	    if($response->code==503) {
		--$page;
		++$sleepTime;
		print " retrying (timeout $sleepTime)\n";
	    }
	    else {
		print "\n";
	    }
	}
	# ++$page;
	$one = 2;
	sleep($sleepTime);
    }
}
