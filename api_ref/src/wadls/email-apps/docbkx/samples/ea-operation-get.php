<?php
header('Content-Type:  text/plain');
require_once 'ApiServer.php';

$client = new  ApiServer();

$response = $client->get(
    '/customers/me/domains',
    'text/xml');
    
echo  $response->getResponseCode() . "\r\n" .
      $response->getHeader("x-error-message") . "\r\n" .
      $response->getBody();
?>