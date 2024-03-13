import boto3
import os

def lambda_handler(event, context):
    try:
        # Check if the event has 'Records' key
        if 'Records' in event:
            # Get the S3 bucket and object information from the first record
            bucket = event['Records'][0]['s3']['bucket']['name']
            key = event['Records'][0]['s3']['object']['key']

            # Check if the uploaded object is the deployment package
            if os.path.basename(key) == 'deployment-package.zip':
                # Create a CodeDeploy client
                codedeploy = boto3.client('codedeploy')

                # Specify your CodeDeploy application and deployment group names
                application_name = 'cross-s3-deploy'
                deployment_group_name = 'cross-s3-ec2-deploy'

                # Create a deployment
                response = codedeploy.create_deployment(
                    applicationName=application_name,
                    deploymentGroupName=deployment_group_name,
                    revision={
                        'revisionType': 'S3',
                        's3Location': {
                            'bucket': bucket,
                            'key': key,
                            'bundleType': 'zip'
                        }
                    }
                )

                print(f"CodeDeploy Deployment created: {response['deploymentId']}")
            else:
                print("Skipping deployment for non-matching object")
        else:
            print("No 'Records' key found in the event. Exiting.")
    except Exception as e:
        print(f"Error: {e}")

    return {
        'statusCode': 200,
        'body': 'Lambda function executed successfully!'
    }
