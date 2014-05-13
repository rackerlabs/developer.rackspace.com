<?php
header('Content-Type:  text/plain');
require_once 'ApiServer.php';

$client = new  ApiServer();

$fields  = Array(
    'serviceType' => 'exchange',
    'exchangeMaxNumMailboxes' => '4');
    
$response = $client->post(
    '/customers/me/domains/newdomain.com',
    $fields);
    
echo  $response->getResponseCode() . "\r\n" .
      $response->getHeader("x-error-message") . "\r\n" .
      $response->getBody();
?>