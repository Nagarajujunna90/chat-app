#!/usr/bin/env bash
#sudo mkdir "chat-app"
##sudo rm -rf /home/ec2-user/chat-app
directory="/home/ec2-user/chat-app"
if [ ! -d "$directory" ]; then
    sudo mkdir -p "$directory"
    echo "Directory '$directory' created."
else
    sudo echo "Directory '$directory' already exists."
fi