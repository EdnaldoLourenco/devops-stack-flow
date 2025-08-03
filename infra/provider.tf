provider "azurerm" {
  features {}
}


terraform {
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = "~>4.38.1"
    }
  }
  backend "azurerm" {
    resource_group_name  = "rg-main"
    storage_account_name = "stprojeto"
    container_name       = "tfstate"
    key                  = "terraform.tfstate"
  }

}