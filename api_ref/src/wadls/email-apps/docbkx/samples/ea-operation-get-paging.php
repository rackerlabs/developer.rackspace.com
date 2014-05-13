<?php
header('Content-Type: text/plain');
require_once 'ApiServer.php';

$client = new ApiServer();
$format = 'application/json';
$domain = 'apiPagingTest.com';

// Get mailboxes. page size defaults to 50.
$url = '/customers/me/domains/' . $domain . '/ex/mailboxes';
$response = $client->get($url, $format);

$body = $response->getBody();
$result = json_decode($response->getBody());
echo $body . "\n";

// check results
$totalSize = $result->{"total"};
$resultSize = $result->{"size"};

// If there are more results, read the rest.
if($totalSize > $resultSize)
{
    // start at last read index and set page size
    $offset = $resultSize;
    $pageSize = 50;
    
    // read up to the next 50
    $url = '/customers/me/domains/' . $domain . '/ex/mailboxes?size='
                            . $pageSize . '&offset=' . $offset;
    $response = $client->get($url, $format);
    echo $response->getBody(). "\n";
}

?>