#!/bin/zsh

#include header files
. ./test.h

test nft

nft.context "dev"
nft.namespace "auth"
nft.type "soak"
nft.deployApp

