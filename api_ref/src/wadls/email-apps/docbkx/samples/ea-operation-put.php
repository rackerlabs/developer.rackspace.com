<?php
header('Content-Type:  text/plain');
require_once 'ApiServer.php';

$client = new  ApiServer();

$fields= Array(
    'displayName' => 'My Display', 
    'password' => 'testPass12#', 
    'size' => '10240', 
    'enabled' => 'true');
    
$response = $client->put(
    '/customers/me/domains/testdomain.com/rs/mailboxes/test.mailbox',
    $fields);
    
echo  $response->getResponseCode() . "\r\n" .
      $response->getHeader("x-error-message") . "\r\n" .
      $response->getBody();
?>